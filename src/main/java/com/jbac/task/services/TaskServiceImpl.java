package com.jbac.task.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbac.task.dao.ITaskDao;
import com.jbac.task.entity.Task;
@Service
public class TaskServiceImpl implements ITaskService{

	@Autowired
	private ITaskDao taskDao;
	
	@Override
	public List<Task> findAll() {
		return(List<Task>)taskDao.findAll();
	}

	@Override
	public void save(Task task) {
		taskDao.save(task);
	}

	@Override
	public List<Task> getTasksUser(Long id) {
		return(List<Task>)taskDao.findByUserId(id);
	}

	@Override
	public Task findById(Long id) {
		return taskDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		taskDao.deleteById(id);
	}

}
