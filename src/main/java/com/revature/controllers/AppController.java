package com.revature.controllers;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.revature.models.ers_user;
import com.revature.services.UserServices;

@Controller
public class AppController {
	
	@Autowired 
	UserServices userService;
	
	@RequestMapping("/")
	public String viewHomePage()
	{	
		return "index.jsp";
	}
	
	@RequestMapping("/login")
	public ModelAndView login()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("error", "Username and/or Password is wrong!");
		modelAndView.setViewName("login.jsp");
		
		return modelAndView;
	}
	
	@PostMapping("/dologin")
	public ModelAndView doLogin(@RequestParam("username") String username, @RequestParam("password") String password )
	{
		ModelAndView modelAndView = null;
		ArrayList<ers_user> usersInDatabase = userService.getUserByUsername("test");
		
		for (int usersInDatabaseIndex = 0; usersInDatabaseIndex < usersInDatabase.size();usersInDatabaseIndex++)
		{
			ers_user user = usersInDatabase.get(usersInDatabaseIndex);
			
			if (user.getErs_username().equals(username) && user.getErs_password().equals(password))
			{
				modelAndView = new ModelAndView("redirect:/");
				return modelAndView;
			}
		}
		
		modelAndView = new ModelAndView("redirect:/login?error=\"yes\"");
		return modelAndView;
	}
	
	@RequestMapping("/index")
	public String index()
	{
		return "index";
	}
}
