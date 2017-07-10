package com.noisyle.demo.hibernate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noisyle.demo.hibernate.dao.TaskDao;
import com.noisyle.demo.hibernate.entity.Task;
import com.noisyle.demo.hibernate.entity.TaskExecute;
import com.noisyle.demo.hibernate.service.TaskService;

@Repository("taskService")
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskDao taskDao;

	public List<Task> findAll() {
		List<Task> tasks = taskDao.findAll();
		return tasks;
	}
	public List<TaskExecute> findAllExecute() {
		List<TaskExecute> executes = taskDao.findAllExecute();
		return executes;
	}
	public List<TaskExecute> findAllExecuteImmediately() {
		List<TaskExecute> executes = taskDao.findAllExecuteImmediately();
		return executes;
	}
}
