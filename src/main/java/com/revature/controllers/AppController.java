package com.revature.controllers;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.revature.models.ers_user;
import com.revature.services.UserServices;

import antlr.collections.List;

@Controller
public class AppController {
	
	@Autowired 
	UserServices userService;
	//UserController userController  = null;
	
	AppController()
	{
	}
	
	@RequestMapping("/")
	public ModelAndView viewHomePage(HttpSession httpSession )
	{	
		ModelAndView modelAndView = null; 
		String redirectString = "redirect:/main";
		@SuppressWarnings("unchecked")
		ArrayList<String> messages = (ArrayList<String>) httpSession.getAttribute("SPRING_BOOT_SESSION_MESSAGES");
		/*
		 TODO(): This is not a good security measure.
		 IDEAS:
		 When user login, store a hash something and put it in the database and session array, then in the index logic
		 get each hash in the database and see if it matches a hash in the session array.
		*/
		if (messages == null)
		{
			redirectString = "redirect:/login";
			modelAndView = new ModelAndView(redirectString);
			return modelAndView;
		} 
			
		modelAndView = new ModelAndView();
		modelAndView.setViewName("index.jsp");
		
		
		return modelAndView;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam(required = false) String error,HttpSession httpSession )
	{
		
		ModelAndView modelAndView = new ModelAndView();
		if (error != null)
		{
			if (error.equals("yes"))
			{
				modelAndView.addObject("error", "Username and/or Password is wrong!");
			}
		}
		modelAndView.setViewName("login.jsp");
		
		return modelAndView;
	}
	
	@PostMapping("/dologin")
	public ModelAndView doLogin(@RequestParam("username") String username, @RequestParam("password") String password,  HttpServletRequest httpServletRequest )
	{
		ModelAndView modelAndView = null;
		String nextPage = null;
		ers_user result = null;
		
		nextPage = "redirect:/login?error=yes";
		
		ArrayList<ers_user> usersInDatabase = userService.getUserByUsername(username);
		
		for (int usersInDatabaseIndex = 0; usersInDatabaseIndex < usersInDatabase.size();usersInDatabaseIndex++)
		{
			result = usersInDatabase.get(usersInDatabaseIndex);
			
			if (result.getErs_username().equals(username) && result.getErs_password().equals(password))
			{
				@SuppressWarnings("unchecked")
				ArrayList<ers_user>  session = (ArrayList<ers_user>) httpServletRequest.getSession().getAttribute("SPRING_BOOT_SESSION_MESSAGES");
				if (session == null)
				{
					session = new ArrayList<>();
					httpServletRequest.getSession().setAttribute("SPRING_BOOT_SESSION_MESSAGES", session);
					session.add(result);
					httpServletRequest.getSession().setAttribute("SPRING_BOOT_SESSION_MESSAGES", session);
				}
				nextPage = "redirect:/";
				break;
			}
		}
		
		modelAndView = new ModelAndView(nextPage);
		return modelAndView;
	}
	

}
