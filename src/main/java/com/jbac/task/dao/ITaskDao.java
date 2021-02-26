package com.jbac.task.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jbac.task.entity.Task;

public interface ITaskDao extends CrudRepository<Task,Long> {
	
	public List<Task> findByUserId(Long id);
	

}
