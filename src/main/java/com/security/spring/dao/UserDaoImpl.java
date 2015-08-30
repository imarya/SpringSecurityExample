package com.security.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.spring.domain.User;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findUser(int userId) {
		return (User) sessionFactory.getCurrentSession()
				.get(User.class, userId);
	}

	@Override
	public User findUserByName(String userName) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				User.class);
		criteria.add(Restrictions.eq("name", userName));
		return (User) criteria.uniqueResult();
	}

	@Override
	public List<User> getAllUser() {
		return (List<User>) sessionFactory.getCurrentSession()
				.createQuery("from user").list();
	}

}
