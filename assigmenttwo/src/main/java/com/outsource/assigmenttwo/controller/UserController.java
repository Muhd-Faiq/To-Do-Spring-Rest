package com.outsource.assigmenttwo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.outsource.assigmenttwo.model.User;
import com.outsource.assigmenttwo.model.Task;
import com.outsource.assigmenttwo.model.LogIn;
import com.outsource.assigmenttwo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	
	
	//for user and task
	@CrossOrigin
	@GetMapping("/user")
	public ResponseEntity<List<User>> retrieveAllUser() {
//		return userService.retrieveAllUser();
		List<User> userlist = userService.retrieveAllUser();
		
		
		if (userlist == null || userlist.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/user/{userId}/task")
	public ResponseEntity<List<Task>> retrieveTaskForUser(@PathVariable int userId) {
//		return userService.retrieveTask(userId);
		List<Task> tasklist = userService.retrieveTask(userId);
		
		
		if (tasklist == null || tasklist.isEmpty()) {
			return new ResponseEntity<List<Task>>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<Task>>(tasklist, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<User>> retrieveSpecificUser(@PathVariable int userId) {
//		return userService.retrieveUser(userId);
		List<User> userlist = userService.retrieveUser(userId);
		
		
		if (userlist == null || userlist.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/user/{userId}/{taskId}")
	public ResponseEntity<List<Task>> retrieveSpecificTask(@PathVariable int userId, @PathVariable int taskId) {
//		return userService.retrieveSpecificTask(userId,taskId);
		
		List<Task> tasklist = userService.retrieveSpecificTask(userId,taskId);
		
		
		if (tasklist == null || tasklist.isEmpty()) {
			return new ResponseEntity<List<Task>>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<Task>>(tasklist, HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("/user/signin")
	public ResponseEntity<List<User>> signInUser(@RequestBody LogIn newLogIn) {
//	public List<User> signInUser(@RequestBody LogIn newLogIn) {
		List<User> login = userService.funclogIn(newLogIn);
		
		
		if (login == null || login.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<User>>(login, HttpStatus.OK);

	}
	
	
	@CrossOrigin
	@PostMapping("/user/register")
	public ResponseEntity<User> registerUser(@RequestBody User newUser) {
		System.out.println("newUser");
		int user = userService.funcRegister(newUser);
		

		if (user != 0)
			return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
			
		
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}

	@CrossOrigin
	@PostMapping("/user/taskinsert")
	public ResponseEntity<Task> registerUser(@RequestBody Task newTask) {

		Task task = userService.funcTaskInsert(newTask);
		

		if (task.getId()!=0)
			return new ResponseEntity<Task>(newTask, HttpStatus.CREATED);
			
		
		return new ResponseEntity<Task>(HttpStatus.BAD_REQUEST);
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/taskdelete/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable int id) {

        int isRemoved = userService.deleteTask(id);

        if (isRemoved!= 0) {
        	return new ResponseEntity<Task>(HttpStatus.OK);
        }
        return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        
    }
	
	@CrossOrigin
	@PutMapping("/taskupdate")
	public ResponseEntity<Task> updateCustomer(@RequestBody Task task) {
		int resultupdate=userService.updateTask(task);
		if (resultupdate!= 0) {
        	return new ResponseEntity<Task>(HttpStatus.OK);
        }
		return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);
//		try {
//			return new ResponseEntity<Task>(userService.updateTask(task), HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}


}