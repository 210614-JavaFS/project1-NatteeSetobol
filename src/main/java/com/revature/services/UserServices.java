package com.revature.services;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.ers_user;
import com.revature.repos.user_dao;

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
}
