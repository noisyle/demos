package com.noisyle.demo.springdemo.dao;

import java.util.List;

import com.noisyle.demo.springdemo.entity.Task;
import com.noisyle.demo.springdemo.entity.TaskExecute;

public interface TaskDao {
	public List<Task> findAll();
	public List<TaskExecute> findAllExecute();
	public List<TaskExecute> findAllExecuteImmediately();
}
