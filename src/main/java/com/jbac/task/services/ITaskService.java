package com.jbac.task.services;

import java.util.List;

import com.jbac.task.entity.Task;

public interface ITaskService {
	
	public List<Task> findAll();
	
	public void save(Task task);
	
	
	public List<Task>getTasksUser(Long id);
	
	public Task findById(Long id);
	
	public void delete(Long id);
}
