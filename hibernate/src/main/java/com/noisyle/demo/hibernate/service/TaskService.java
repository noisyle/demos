package com.noisyle.demo.hibernate.service;

import java.util.List;

import com.noisyle.demo.hibernate.entity.Task;
import com.noisyle.demo.hibernate.entity.TaskExecute;

public interface TaskService {
	public List<Task> findAll();
	public List<TaskExecute> findAllExecute();
	public List<TaskExecute> findAllExecuteImmediately();
}
