package com.jbac.task.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbac.task.dao.IUserDao;
import com.jbac.task.entity.User;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public User checkUserLogin(User user) {
		return (User)userDao.findByEmailAndPassword(user.getEmail(),user.getPassword());
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	public User findUser(User user) {
		return userDao.findByEmail(user.getEmail());
	}

}
