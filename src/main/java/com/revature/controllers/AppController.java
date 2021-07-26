package com.revature.controllers;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.revature.models.ers_reimbursement;
import com.revature.models.ers_reimbursement_status;
import com.revature.models.ers_reimbursment_type;
import com.revature.models.ers_user;
import com.revature.models.ers_users_roles;
import com.revature.repos.reimbursment_dao;
import com.revature.repos.reimbursment_type_dao;
import com.revature.services.ReimbursementService;
import com.revature.services.ReimbursementTypeServices;
import com.revature.services.RembursmentStatusServices;
import com.revature.services.UserRoleServices;
import com.revature.services.UserServices;

import antlr.collections.List;

@Controller
public class AppController {
	
	@Autowired 
	UserServices userService;
	
	@Autowired
	UserRoleServices userRoleServices;
	
	@Autowired
	ReimbursementService reimbursementService;
	
	@Autowired
	RembursmentStatusServices reimbursementStatusService;
	
	@Autowired
	ReimbursementTypeServices reimbursementTypeService;
	
	
	@RequestMapping("/")
	public ModelAndView viewHomePage(HttpSession httpSession )
	{	
		ModelAndView modelAndView = null; 
		String redirectString = "redirect:/main";
		String userRole = "";
		ArrayList<HashMap<String, String>> pendingTickets = new ArrayList<>();
		ArrayList<HashMap<String, String>> approvedTickets = new ArrayList<>();
		ArrayList<HashMap<String, String>> disapprovedTickets = new ArrayList<>();
	
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
			// TODO(): Make this more dynamic by using REST API
			ArrayList<ers_reimbursement> reimbursementTickets = reimbursementService.getAllTicketsByUserId( (int) currentUser.getErs_id());
			
			for (int ticketIndex = 0; ticketIndex < reimbursementTickets.size(); ticketIndex++)
				
			{
				ers_reimbursement ticket = reimbursementTickets.get(ticketIndex);
				ArrayList<ers_reimbursement_status> ticketStatusArray =  reimbursementStatusService.getAllStatusById(ticket.getReimb_status_type_id());
				ers_reimbursement_status ticketStatus = ticketStatusArray.get(0);
				

				HashMap<String, String> reimbruseTicket = new HashMap<String, String>();
					
				ArrayList<ers_reimbursment_type> typeArray =	reimbursementTypeService.FindAllReimbursmentType(ticket.getReimb_status_id());
				if (typeArray.size() > 0)
				{
					ers_reimbursment_type type = typeArray.get(0);
					
					reimbruseTicket.put("type",type.getReimb_type());
					reimbruseTicket.put("date",ticket.getReimp_submitted().toString());
					reimbruseTicket.put("amount",String.valueOf(ticket.getReimb_amount()));
					
					if (ticketStatus.getReimb_status().equals("unapproved"))
					{
						pendingTickets.add(reimbruseTicket);
					} else 
					if (ticketStatus.getReimb_status().equals("approved"))
					{
						approvedTickets.add(reimbruseTicket);
					} else 
					if (ticketStatus.getReimb_status().equals("denied"))
					{
						disapprovedTickets.add(reimbruseTicket);
					}
				}
			}
		
			modelAndView.addObject("pendingTickets",pendingTickets);
			modelAndView.addObject("approvedTickets",approvedTickets);
			modelAndView.addObject("disapprovedTickets",disapprovedTickets);
			
			modelAndView.setViewName("index.jsp");
		
		} else
		if (userRole.equals("Manager"))
		{
			modelAndView.setViewName("Manager.jsp");
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
