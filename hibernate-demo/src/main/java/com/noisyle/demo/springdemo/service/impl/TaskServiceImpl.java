package com.noisyle.demo.springdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noisyle.demo.springdemo.dao.TaskDao;
import com.noisyle.demo.springdemo.entity.Task;
import com.noisyle.demo.springdemo.entity.TaskExecute;
import com.noisyle.demo.springdemo.service.TaskService;

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
