package com.jbac.task.dao;

import org.springframework.data.repository.CrudRepository;

import com.jbac.task.entity.User;

public interface IUserDao extends CrudRepository<User,Long>{
	
	public User findByEmail(String email);
	
	public User findByEmailAndPassword(String email,String password);
	
	
}
