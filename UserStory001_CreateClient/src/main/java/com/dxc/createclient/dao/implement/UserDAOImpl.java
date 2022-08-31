package com.dxc.createclient.dao.implement;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.createclient.dao.UserDAO;
import com.dxc.createclient.entity.User;


@Transactional
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public List<User> listUser() {
		List<User> list = new ArrayList<User>();
		Session session = sessionFactory.getCurrentSession();
		list = session.createQuery("from User", User.class).getResultList();
		session.flush();
		return list;
	}

	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();

		//Set string ID
		String id;
		if (countUser()<10) {
			id = "US00000" + countUser();
		} else if (countUser()<100) {
			id = "US0000" + countUser();
		}else if (countUser()<1000) {
			id = "US000" + countUser();
		}else if (countUser()<10000) {
			id = "US00" + countUser();
		}else {
			id = "US0" + countUser();
		}
		user.setClientNumber(id);
		try {
			session.persist(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public User findUser(String id) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.eq("identity", id));
		return (User) crit.uniqueResult();
	}

	public int countUser() {
		int number = listUser().size() + 1;
		return number;
	}

	public void updateUserInfor(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.load(User.class, user.getClientNumber());
		session.update(user);
		System.out.println("Update sucessful!");
	}

	public User findUserByClientNumber(String clientNumber) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.eq("clientNumber", clientNumber));
		return (User) crit.uniqueResult();
	}

}
