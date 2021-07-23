package com.revature.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.revature.models.ers_user;
import com.revature.services.ReimbursementService;
import com.revature.services.ReimbursementTypeServices;
import com.revature.services.RembursmentStatusServices;
import com.revature.services.UserRoleServices;
import com.revature.services.UserServices;

@Controller
public class UserController {
	
	@Autowired 
	UserServices userService;
	
	@Autowired
	ReimbursementTypeServices reimbursementTypeService;
	@Autowired
	RembursmentStatusServices rembursementStatusService;

	@Autowired
	ReimbursementService reimbursementService;
	
	@PostMapping("/doReimbursment")
	@ResponseBody
	public String doRebursement(@RequestParam("rebursementAmount") float rebursementAmount, @RequestParam("rebursementType") String rebursementType,@RequestParam("rebursementDescription") String rebursementDescription, HttpServletRequest httpServletRequest )
	{
			String result = null;
			@SuppressWarnings("unchecked")
			ArrayList<ers_user>  session = (ArrayList<ers_user>) httpServletRequest.getSession().getAttribute("SPRING_BOOT_SESSION_MESSAGES");
			int reimbursementTypeId =  -1;
			int reimburseMentStatusId = -1;
			int reimbursementId = -1;
			int authorId = -1;
			
			if (session == null)
			{
				result = "{ Error: 'not signed in'}";
			}  else 
			if (session.size() != 1)
			{
				result = "{ Error: 'Too many sessions'}";
			} else {
				// TODO(): I should prob change the user id to a int instead of a long
				ers_user currentUser = session.get(0);
				reimbursementTypeId= reimbursementTypeService.createReimbursmentType(rebursementType);
				reimburseMentStatusId = rembursementStatusService.createRebursementStatus("unapproved");
				reimbursementId =  reimbursementService.CreateReimbursement(rebursementDescription, rebursementAmount, reimbursementTypeId,reimburseMentStatusId , (int) currentUser.getErs_id());
				result = "{ Error: 'none'}";
			}
			
			return result;
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
