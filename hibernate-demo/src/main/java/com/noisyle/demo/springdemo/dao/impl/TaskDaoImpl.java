package com.noisyle.demo.springdemo.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.noisyle.demo.springdemo.dao.TaskDao;
import com.noisyle.demo.springdemo.entity.Task;
import com.noisyle.demo.springdemo.entity.TaskExecute;

@Repository("taskDao")
public class TaskDaoImpl implements TaskDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Task> findAll() {
		List<Task> tasks = this.getCurrentSession().createQuery("from Task").list();
		return tasks;
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskExecute> findAllExecute() {
		List<TaskExecute> executes = this.getCurrentSession().createQuery("from TaskExecute").list();
		return executes;
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskExecute> findAllExecuteImmediately() {
		String sql = "select {e.*}, {t.*} from at_autotask_execute e, at_autotask t where e.autotaskid = t.id";
		List<Object[]> result = this.getCurrentSession().createSQLQuery(sql)
				.addEntity("e", TaskExecute.class)
				.addJoin("t", "e.autotask")
				.list();
		List<TaskExecute> executes = new LinkedList<TaskExecute>();
		for(Object[] row: result){
			executes.add((TaskExecute) row[0]);
		}
		return executes;
	}
}
