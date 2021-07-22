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
import com.revature.models.ers_users_roles;
import com.revature.services.UserRoleServices;
import com.revature.services.UserServices;

import antlr.collections.List;

@Controller
public class AppController {
	
	@Autowired 
	UserServices userService;
	
	@Autowired
	UserRoleServices userRoleServices;

	@RequestMapping("/")
	public ModelAndView viewHomePage(HttpSession httpSession )
	{	
		ModelAndView modelAndView = null; 
		String redirectString = "redirect:/main";
		String userRole = "";
	
		@SuppressWarnings("unchecked")
		ArrayList<ers_user> messages = (ArrayList<ers_user>) httpSession.getAttribute("SPRING_BOOT_SESSION_MESSAGES");
		if (messages == null)
		{
			redirectString = "redirect:/login";
			modelAndView = new ModelAndView(redirectString);
			return modelAndView;
		} 
			
		modelAndView = new ModelAndView();
		
		ers_user currentUser = messages.get(0);
		
		if (currentUser.getUser_role_id() != 0)
		{
			if (userRoleServices != null)
			{
				ArrayList<ers_users_roles> userRoleArray = userRoleServices.findUserRoleByUserRoleId(currentUser.getUser_role_id());
				ers_users_roles userRoles = userRoleArray.get(0);
				userRole = userRoles.getUser_role();
			}
		}
		
		if (userRole.equals("Employee"))
		{
			modelAndView.setViewName("index.jsp");
		} else
		if (userRole.equals("Admin"))
		{
			modelAndView.setViewName("admin.jsp");
		} else {
			redirectString = "redirect:/login";
			modelAndView = new ModelAndView(redirectString);
			
		}
		
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
	


}
