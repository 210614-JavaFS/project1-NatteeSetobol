package com.revature.services;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.ers_user;
import com.revature.models.ers_users_roles;
import com.revature.repos.user_dao;
import com.revature.repos.user_roles_dao;

@Service
public class UserServices {
	@Autowired
	user_dao userDao;
	
	public ArrayList<ers_user> getUserByUsername(String username)
	{
		ArrayList<ers_user> result = null;

		result = userDao.findByUsername(username);
		return result;
	}
	/*
	public String getUserRoleByUserId(long id)
	{
		String result = null;
		ArrayList<ers_user> users = null;
		ers_users_roles userRoles = null;

		users = userDao.findByUserId(id);
		
		for (int usersIndex = 0; usersIndex < users.size(); usersIndex++)
		{
			ers_user user = users.get(usersIndex);
			
			if (user.getErs_id() == id)
			{
				ArrayList<ers_users_roles> userRolesArray = userRoleDAO.findUserRoleByUserRoleId(user.getErs_id());
				userRoles = userRolesArray.get(0);
				result = userRoles.getUser_role();
				break;
			}
		}
		
		
		return result;
	}
	*/
}
