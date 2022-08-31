package com.dxc.createclient.config;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.dxc.createclient.authentication.MyDBAuthenticationService;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	MyDBAuthenticationService myDBAuthenticationService;
	
	private static final Log log = LogFactory.getLog(MyDBAuthenticationService.class);
	
	@Autowired
	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(myDBAuthenticationService);
		
	}
	
	@Override
	protected void configure (HttpSecurity https) throws Exception {
		https.csrf().disable();
		
		//Requirement: Login with User or Admin role
		//If not login, redirect to login page
		https.authorizeRequests().antMatchers("user_list", "userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
		
		//Page only for Admin
		https.authorizeRequests().antMatchers("add_user", "modify_user").access("hasRole('ROLE_ADMIN')");
		
		//Loged in but not have right role
		https.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		//Config for log in form
		https.authorizeRequests().and().formLogin()
		.loginProcessingUrl("/j_spring_security_check")
		.loginPage("/login")
		.defaultSuccessUrl("/user_list")
		.failureUrl("/login?error=true")
		.usernameParameter("userName")
		.passwordParameter("password")
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
		
	}
	
	
}
 
