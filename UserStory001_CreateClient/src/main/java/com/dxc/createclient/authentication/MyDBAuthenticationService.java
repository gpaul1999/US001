package com.dxc.createclient.authentication;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dxc.createclient.dao.AccountDAO;
import com.dxc.createclient.entity.Account;

@Service
public class MyDBAuthenticationService implements UserDetailsService{
	
	@Autowired
	private AccountDAO accountDAO;
	
	private static final Log log = LogFactory.getLog(MyDBAuthenticationService.class);
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountDAO.findAccount(username);
		System.out.println("Account: " + account);
		
		if (account == null) {
			log.error("==========USER LOGIN FAIL==========");
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		
		String role = account.getUserRole();
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
		
		grantList.add(authority);
		
		boolean enabled = account.isActive();
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		UserDetails userDetails = new User(account.getUserName(), account.getPassword(), 
				enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantList);
		
		return userDetails;
	}

}
