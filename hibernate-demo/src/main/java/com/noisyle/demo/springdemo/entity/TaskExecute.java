package com.noisyle.demo.springdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "at_autotask_execute")
public class TaskExecute {
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 6)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "autotaskid")
	private Task autotask;
	private String executetime;
	public Task getAutotask() {
		return autotask;
	}
	public void setAutotask(Task autotask) {
		this.autotask = autotask;
	}
	public String getExecutetime() {
		return executetime;
	}
	public void setExecutetime(String executetime) {
		this.executetime = executetime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
