package com.outsource.assigmenttwo.model;

//import com.outsource.assigmenttwo.model.Task;

//import java.util.List;

public class Task {
	private int id;
	private String taskName;
	private String taskDescription;
	private String taskCondition;
	private int userId;

	// Needed by Caused by: com.fasterxml.jackson.databind.JsonMappingException:
	// Can not construct instance of com.in28minutes.springboot.model.Task:
	// no suitable constructor found, can not deserialize from Object value
	// (missing default constructor or creator, or perhaps need to add/enable
	// type information?)
	public Task() {

	}

	public Task(int id, String taskName, String taskDescription, String taskCondition, int userId) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskCondition = taskCondition;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskDescription() {
		return taskDescription;
	}
	
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getTaskName() {
		return taskName;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskCondition() {
		return taskCondition;
	}
	
	public void setTaskCondition(String taskCondition) {
		this.taskCondition = taskCondition;
	}
	public int getuserId() {
		return userId;
	}

	public void setuserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return String.format(
				"Task [id=%s, taskName=%s, taskDescription=%s, taskCondition=%s, taskCondition=%s]", id, taskName,
				taskDescription, taskCondition, userId);
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Task other = (Task) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}

}