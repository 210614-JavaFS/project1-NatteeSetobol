package com.revature.controllers;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.models.ers_user;
import com.revature.services.UserServices;

@Controller
public class AppController {
	
	@Autowired 
	UserServices userService;
	
	@RequestMapping("/")
	public String viewHomePage()
	{	
	
		ArrayList<ers_user> usersInDatabase = userService.getUserByUsername("test");
		
		System.out.println("There are " + usersInDatabase.size());
		return "index.jsp";
	}
	
	@RequestMapping("/index")
	public String index()
	{
		return "index";
	}
}
