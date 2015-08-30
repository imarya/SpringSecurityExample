package com.security.spring.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.spring.dao.IUserDao;
import com.security.spring.domain.Role;
import com.security.spring.domain.User;

@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private IUserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDao.findUserByName(username);
		if (null != user) {
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for (Role role : user.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(role.getName()));
			}

			org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(
					username, user.getPassword(), authorities);

			return securityUser;
		} else {
			throw new UsernameNotFoundException("Bad Credentials!!");
		}
	}
}
