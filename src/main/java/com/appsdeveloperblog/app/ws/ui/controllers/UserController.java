package com.appsdeveloperblog.app.ws.ui.controllers;

import com.appsdeveloperblog.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;
import com.appsdeveloperblog.app.ws.userservice.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {

	//Map<String, UserRest> users;

//	Logger logger = LoggerFactory();
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<ArrayList<UserRest>> getUsers(@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="limit", defaultValue="50") int limit,
			@RequestParam(value="sort", defaultValue = "desc", required = false) String sort)
	{
		ArrayList<UserRest> users = userService.getUsers();
		if (users != null) {
			logger.info("#{} users retrieved", users.size());
		} else {
			logger.info("no users retrieved");
		}

		return new ResponseEntity<ArrayList<UserRest>>(users, HttpStatus.OK);
		//return "get users was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
	}
	
	@GetMapping(path="/{userId}", 
			produces =  { 
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			} )
	public ResponseEntity<UserRest> getUser(@PathVariable int userId)
	{
		UserRest user = userService.getUser(userId);
		if(user != null)
		{
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(
			consumes =  {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			},
			produces =  { 
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			} )
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails)
	{

		UserRest user = userService.createUser(userDetails);
		logger.info("user #{} created", user.getUserId());
		return new ResponseEntity<UserRest>(user, HttpStatus.OK);
	}
	
	@PutMapping(path="/{userId}",
			consumes =  {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			},
			produces =  { 
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE
			} )
	public UserRest updateUser(@PathVariable int userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails)
	{
		 UserRest storedUserDetails = userService.getUser(userId);
		 storedUserDetails.setFirstName(userDetails.getFirstName());
		 storedUserDetails.setLastName(userDetails.getLastName());

		 // @TODO
		 //users.put(userId, storedUserDetails);
		 
		 return storedUserDetails;
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id)
	{
		// @TODO
		//users.remove(id);
		
		return ResponseEntity.noContent().build();
	}
}
