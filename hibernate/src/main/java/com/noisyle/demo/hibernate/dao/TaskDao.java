package com.noisyle.demo.hibernate.dao;

import java.util.List;

import com.noisyle.demo.hibernate.entity.Task;
import com.noisyle.demo.hibernate.entity.TaskExecute;

public interface TaskDao {
	public List<Task> findAll();
	public List<TaskExecute> findAllExecute();
	public List<TaskExecute> findAllExecuteImmediately();
}
