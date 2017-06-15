package com.noisyle.demo.springdemo.service;

import java.util.List;

import com.noisyle.demo.springdemo.entity.Task;
import com.noisyle.demo.springdemo.entity.TaskExecute;

public interface TaskService {
	public List<Task> findAll();
	public List<TaskExecute> findAllExecute();
	public List<TaskExecute> findAllExecuteImmediately();
}
