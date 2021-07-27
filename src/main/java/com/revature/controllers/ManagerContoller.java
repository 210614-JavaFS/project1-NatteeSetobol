package com.revature.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ers_reimbursement;
import com.revature.models.ers_reimbursement_status;
import com.revature.models.ers_reimbursment_type;
import com.revature.models.ers_user;
import com.revature.models.ers_users_roles;
import com.revature.services.ReimbursementService;
import com.revature.services.ReimbursementTypeServices;
import com.revature.services.RembursmentStatusServices;
import com.revature.services.UserRoleServices;
import com.revature.services.UserServices;

@Controller
public class ManagerContoller {
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
	
	
	@PostMapping("/approveTicket")
	@ResponseBody
	public String approveTicket(@RequestParam("ticketId") int ticketId,HttpServletRequest httpServletRequest)
	{
		String userRole = "";
		String result = null;
		ObjectMapper objectMapper = new ObjectMapper();
		
		@SuppressWarnings("unchecked")
		ArrayList<ers_user>  session = (ArrayList<ers_user>) httpServletRequest.getSession().getAttribute("SPRING_BOOT_SESSION_MESSAGES");
		if (session == null)
		{
			HashMap<String, String> errorMessage = new HashMap<>();
			errorMessage.put("error", "invalid Session");
			try {
				result = objectMapper.writeValueAsString(errorMessage);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			
			ers_user currentUser = session.get(0);
			if (currentUser.getUser_role_id() != 0)
			{
				ArrayList<ers_users_roles> userRoleArray = userRoleServices.findUserRoleByUserRoleId(currentUser.getUser_role_id());
				ers_users_roles userRoles = userRoleArray.get(0);
				userRole = userRoles.getUser_role();
				
				if (userRole.equals("Manager"))
				{
					Timestamp today = new Timestamp(System.currentTimeMillis());
					reimbursementService.updateTicketStatus(ticketId, (int) currentUser.getErs_id(), today, 5);
				}
			}
		}
		return result;
	}
	
	@PostMapping("/disapproveTicket")
	@ResponseBody
	public String disapproveTicket(@RequestParam("ticketId") int ticketId,HttpServletRequest httpServletRequest)
	{
		String userRole = "";
		String result = null;
		ObjectMapper objectMapper = new ObjectMapper();
		
		@SuppressWarnings("unchecked")
		ArrayList<ers_user>  session = (ArrayList<ers_user>) httpServletRequest.getSession().getAttribute("SPRING_BOOT_SESSION_MESSAGES");
		if (session == null)
		{
			HashMap<String, String> errorMessage = new HashMap<>();
			errorMessage.put("error", "invalid Session");
			try {
				
				result = objectMapper.writeValueAsString(errorMessage);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			
			ers_user currentUser = session.get(0);
			if (currentUser.getUser_role_id() != 0)
			{
				ArrayList<ers_users_roles> userRoleArray = userRoleServices.findUserRoleByUserRoleId(currentUser.getUser_role_id());
				ers_users_roles userRoles = userRoleArray.get(0);
				userRole = userRoles.getUser_role();
				
				if (userRole.equals("Manager"))
				{
					Timestamp today = new Timestamp(System.currentTimeMillis());
					reimbursementService.updateTicketStatus(ticketId, (int) currentUser.getErs_id(), today, 7);
				}
			}
		}
		return result;
	}
	
	@GetMapping("/api/GetAllUnapproveTickets")
	@ResponseBody
	public String getAllUnapprovedTickets( HttpServletRequest httpServletRequest) throws JsonProcessingException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		String result = null;
		String userRole = "";
		
		@SuppressWarnings("unchecked")
		ArrayList<ers_user>  session = (ArrayList<ers_user>) httpServletRequest.getSession().getAttribute("SPRING_BOOT_SESSION_MESSAGES");
		ArrayList<HashMap<String, String>> pendingTickets = new ArrayList<>();
		
		if (session == null)
		{
			HashMap<String, String> errorMessage = new HashMap<>();
			errorMessage.put("error", "invalid Session");
			result = objectMapper.writeValueAsString(errorMessage);
		} else {
			
			ers_user currentUser = session.get(0);
			
			if (currentUser.getUser_role_id() != 0)
			{
				
				ArrayList<ers_users_roles> userRoleArray = userRoleServices.findUserRoleByUserRoleId(currentUser.getUser_role_id());
				ers_users_roles userRoles = userRoleArray.get(0);
				userRole = userRoles.getUser_role();
				
				if (userRole.equals("Manager"))
				{
			
					ArrayList<ers_reimbursement> reimbursementTickets = reimbursementService.getAllTicketsByStatus();
		
					for (int ticketIndex = 0; ticketIndex < reimbursementTickets.size(); ticketIndex++)
					{
						ers_reimbursement ticket = reimbursementTickets.get(ticketIndex);
						ArrayList<ers_reimbursement_status> ticketStatusArray =  reimbursementStatusService.getAllStatusById(ticket.getReimb_status_type_id());
						ers_reimbursement_status ticketStatus = ticketStatusArray.get(0);
			
						if (ticketStatus.getReimb_status().equals("unapproved"))
						{
							HashMap<String, String> reimbruseTicket = new HashMap<String, String>();
				
							ArrayList<ers_reimbursment_type> typeArray =	reimbursementTypeService.FindAllReimbursmentType(ticket.getReimb_status_id());
							ers_reimbursment_type type = typeArray.get(0);
						
							ArrayList<ers_user> userInfo =  userService.findByUserId((long)ticket.getReimb_author());
							ers_user ticketAuthor = userInfo.get(0);
				
							reimbruseTicket.put("authorId",String.valueOf(ticket.getReimb_author()));
							reimbruseTicket.put("author", ticketAuthor.getErs_username());
							reimbruseTicket.put("descrip", ticket.getReimp_description());
							reimbruseTicket.put("date",ticket.getReimp_submitted().toString());
							reimbruseTicket.put("number",String.valueOf(ticket.getReimp_id()));
							reimbruseTicket.put("amount",String.valueOf(ticket.getReimb_amount()));
							pendingTickets.add(reimbruseTicket);
						}
					}
					
					result = objectMapper.writeValueAsString(pendingTickets);
				
		
				}
				
			}
		}
		
		return result;
	}
	
	@GetMapping("/api/GetAllTickets")
	@ResponseBody
	public String getAllTickets( HttpServletRequest httpServletRequest) throws JsonProcessingException
	{
		String result = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String userRole = "";
		
		@SuppressWarnings("unchecked")
		ArrayList<ers_user>  session = (ArrayList<ers_user>) httpServletRequest.getSession().getAttribute("SPRING_BOOT_SESSION_MESSAGES");
		ArrayList<HashMap<String, String>> pendingTickets = new ArrayList<>();
		
		if (session == null)
		{
			HashMap<String, String> errorMessage = new HashMap<>();
			errorMessage.put("error", "invalid Session");
			result = objectMapper.writeValueAsString(errorMessage);
		} else {
			
			ers_user currentUser = session.get(0);
			
			if (currentUser.getUser_role_id() != 0)
			{
				ArrayList<ers_users_roles> userRoleArray = userRoleServices.findUserRoleByUserRoleId(currentUser.getUser_role_id());
				ers_users_roles userRoles = userRoleArray.get(0);
				userRole = userRoles.getUser_role();
				
				if (userRole.equals("Manager"))
				{
					ArrayList<ers_reimbursement> reimbursementTickets = reimbursementService.getAllTicketsByStatus();
		
					for (int ticketIndex = 0; ticketIndex < reimbursementTickets.size(); ticketIndex++)
					{
						ArrayList<ers_user> resolverInfo  = null;
						String resolverName = "";
						String timeResolved = "";
						ers_reimbursement ticket = reimbursementTickets.get(ticketIndex);
						ArrayList<ers_reimbursement_status> ticketStatusArray =  reimbursementStatusService.getAllStatusById(ticket.getReimb_status_type_id());
						ers_reimbursement_status ticketStatus = ticketStatusArray.get(0);
			
						//ticketStatus.getReimb_status().equals("unapproved"))
						
							HashMap<String, String> reimbruseTicket = new HashMap<String, String>();
				
							ArrayList<ers_reimbursment_type> typeArray =	reimbursementTypeService.FindAllReimbursmentType(ticket.getReimb_status_id());
							ers_reimbursment_type type = typeArray.get(0);
						
							ArrayList<ers_user> userInfo =  userService.findByUserId((long)ticket.getReimb_author());
							ers_user ticketAuthor = userInfo.get(0);
							
							if (ticket.getReimb_resolver() != 0)
							{
								resolverInfo =  userService.findByUserId((long)ticket.getReimb_resolver());
								ers_user resolverObject = resolverInfo.get(0);
								resolverName  = resolverObject.getErs_username();
							}
							
							try
							{
								timeResolved = ticket.getReimp_resolved().toString();
							} catch(Exception e)
							{
							}
							if (ticketStatus.getReimb_status().equals("unapproved") == false)
							{
								reimbruseTicket.put("authorId",String.valueOf(ticket.getReimb_author()));
								reimbruseTicket.put("author", ticketAuthor.getErs_username());
								reimbruseTicket.put("descrip", ticket.getReimp_description());
								reimbruseTicket.put("date",ticket.getReimp_submitted().toString());
								reimbruseTicket.put("number",String.valueOf(ticket.getReimp_id()));
								reimbruseTicket.put("amount",String.valueOf(ticket.getReimb_amount()));
								reimbruseTicket.put("ticketStatus", ticketStatus.getReimb_status());
								reimbruseTicket.put("ticketTimeResolve", timeResolved);
								reimbruseTicket.put("resolver",resolverName );
								pendingTickets.add(reimbruseTicket);
							}
						
					}
					
					result = objectMapper.writeValueAsString(pendingTickets);			
				}
			}
		}

		return result;
	}
}
