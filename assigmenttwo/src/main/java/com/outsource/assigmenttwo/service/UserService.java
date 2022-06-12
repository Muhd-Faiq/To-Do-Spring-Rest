package com.outsource.assigmenttwo.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;


import com.outsource.assigmenttwo.model.User;
import com.outsource.assigmenttwo.model.Task;
import com.outsource.assigmenttwo.model.LogIn;

@Component
public class UserService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static List<User> user = new ArrayList<>();


	
	
	//for user and task
	private static final class UserMapper implements RowMapper<User> {
	    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	User emp = new User(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("password"));
	    	if(emp!=null) {
	    		System.out.print("Nice");
	    	}
	    	else {
	    		System.out.print("Not");
	    	}
//	    	emp.setId(rs.getInt("id"));
//	    	emp.setEmpName(rs.getString("name"));
//	    	emp.setAge(rs.getInt("age"));
	    	return emp;
	    }
	  } 

	private static final class TaskMapper implements RowMapper<Task> {
	    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Task emp = new Task(rs.getInt("id"),rs.getString("taskName"),rs.getString("taskDescription"),rs.getString("taskCondition"),rs.getInt("userId"));
	    	if(emp!=null) {
	    		System.out.print("Nice");
	    	}
	    	else {
	    		System.out.print("Not");
	    	}
//	    	emp.setId(rs.getInt("id"));
//	    	emp.setEmpName(rs.getString("name"));
//	    	emp.setAge(rs.getInt("age"));
	    	return emp;
	    }
	  } 
	
	public List<User> retrieveAllUser() {
		String sql="SELECT * FROM `user`";
		return this.jdbcTemplate.query(sql, new UserMapper());
		
//		return user;
	}
	
	public List<Task> retrieveTask(int userId) {
		
		String sql="SELECT * FROM `task` WHERE userId=?";
		return this.jdbcTemplate.query(sql,new Object[] { userId}, new TaskMapper());
//		User user = retrieveUser(userId);
//
//		if (user == null) {
//			return null;
//		}
//
//		return user.getTask();
	}
	
	public List<User> retrieveUser(int userId) {
		String sql="SELECT * FROM `user` WHERE id=?";
		return this.jdbcTemplate.query(sql,new Object[] {userId}, new UserMapper());
	}
	
	public User retrieveUserforLogIn(LogIn login) {
		for (User user : user) {
			if (user.getEmail().equals(login.getEmail()) && user.getPassword().equals(login.getPassword())) {
				return user;
			}
		}
		return null;
	}
	
	public List<User> funclogIn(LogIn newlogin) {
		String sql="SELECT * FROM `user` WHERE email=? AND password=? LIMIT 1";
		return this.jdbcTemplate.query(sql,new Object[] { newlogin.getEmail(), newlogin.getPassword() }, new UserMapper());
	}
	
	public List<Task> retrieveSpecificTask(int userId, int taskId) {
		String sql="SELECT * FROM `task` WHERE id=? AND userId=?";
		return this.jdbcTemplate.query(sql,new Object[] { taskId, userId }, new TaskMapper());
		
	}
	
	public List<User> checkforRegister(String email) {
		String sql="SELECT * FROM `user` WHERE email=? LIMIT 1";
		return this.jdbcTemplate.query(sql,new Object[] { email }, new UserMapper());
	}
	
	public int funcRegister(User newUser) {
		List<User> listuser=checkforRegister(newUser.getEmail());
		if(!listuser.isEmpty()) {
			return 0;
		}
		String sql = "INSERT INTO `user`(`name`, `email`, `password`) " + "VALUES (?, ?, ?)"; 
		return this.jdbcTemplate.update(sql, new Object[] { newUser.getName(), newUser.getEmail(), newUser.getPassword()});
	}
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public Task funcTaskInsert(Task newTask) {
//		String sql = "INSERT INTO `task`(`taskName`, `taskDescription`, `taskCondition`, `userId`) VALUES (?,?,?,?)"; 
//		return this.jdbcTemplate.update(sql, new Object[] { newTask.getTaskName(), newTask.getTaskDescription(), newTask.getTaskCondition(),newTask.getuserId()});
		String sql = "INSERT INTO `task`(`taskName`, `taskDescription`, `taskCondition`, `userId`) VALUES (:taskName, :taskDescription, :taskCondition, :userId)"; 
		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("taskName", newTask.getTaskName())
				.addValue("taskDescription", newTask.getTaskDescription())
				.addValue("taskCondition", newTask.getTaskCondition())
				.addValue("userId", newTask.getuserId());
		namedParameterJdbcTemplate.update(sql, parameters, holder);
		newTask.setId(holder.getKey().intValue());
		return newTask;
	}
	
	public int deleteTask(int id) {
		String sql = "DELETE FROM `task` WHERE id=?"; 
		return this.jdbcTemplate.update(sql, new Object[] {id});
	}
	
	public int updateTask(Task task) {
		String sql = "UPDATE `task` SET taskName=?,taskDescription=?,taskCondition=?,userId=? WHERE id=?"; 
		return this.jdbcTemplate.update(sql, new Object[] {task.getTaskName(),task.getTaskDescription(),task.getTaskCondition(),task.getuserId(),task.getId()});
	}
	
	
	
	
	
	
	
	
	
	
	
	
}