package com.revature.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.models.ers_users_roles;
import com.revature.repos.user_roles_dao;

@Service
public class UserRoleServices {
	@Autowired
	user_roles_dao userRolesDao;
	
	  public ArrayList<ers_users_roles> findUserRoleByUserRoleId(long userId)
	  {
		  return userRolesDao.findUserRoleByUserRoleId(userId);
	  }
}
