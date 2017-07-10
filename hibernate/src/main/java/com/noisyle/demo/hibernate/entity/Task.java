package com.noisyle.demo.hibernate.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "at_autotask")
public class Task {
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 6)
	private Long id;
	private String code;
	private String name;
	@OneToMany(cascade=CascadeType.ALL, mappedBy ="autotask")
	private List<TaskExecute> taskExecute;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TaskExecute> getTaskExecute() {
		return taskExecute;
	}
	public void setTaskExecute(List<TaskExecute> taskExecute) {
		this.taskExecute = taskExecute;
	}
}
