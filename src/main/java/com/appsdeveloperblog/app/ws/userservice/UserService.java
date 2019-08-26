package com.appsdeveloperblog.app.ws.userservice;

import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;

import java.util.ArrayList;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel userDetails);

	ArrayList<UserRest> getUsers();

	UserRest getUser(int userId);
}
