package com.lumesse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lumesse.entity.User;
import com.lumesse.pojo.SpittleUserDetails;
import com.lumesse.repository.UserRepository;
import com.lumesse.service.UserAuthService;

@Service
public class UserAuthServiceImpl implements UserAuthService {

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(
					"Cannot find user with username: " + username);
		}

		return new SpittleUserDetails(user);
	}

}
