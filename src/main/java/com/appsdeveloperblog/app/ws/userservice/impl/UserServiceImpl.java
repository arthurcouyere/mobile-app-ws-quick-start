package com.appsdeveloperblog.app.ws.userservice.impl;

import com.appsdeveloperblog.app.ws.shared.Utils;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;
import com.appsdeveloperblog.app.ws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

	static int counter = 1;

	static int getId() {
		return counter++;
	}

	Map<String, UserRest> users;
	Utils utils;
	
	public UserServiceImpl() {}
	
	@Autowired
	public UserServiceImpl(Utils utils)
	{
		this.utils =utils;
	}
	
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		//String userId = utils.generateUserId();
		String userId = Integer.toString(UserServiceImpl.getId());
		returnValue.setUserId(userId);
		
		if(users == null) users = new HashMap<>();
		users.put(userId, returnValue);
		
		return returnValue;
	}

	@Override
	public ArrayList<UserRest> getUsers() {
		return new ArrayList<>(users.values());
	}

	@Override
	public UserRest getUser(int userId) {
		return users.get(Integer.toString(userId));
	}
}
