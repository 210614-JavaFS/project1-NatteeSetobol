package com.revature.services;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.ers_reimbursement;
import com.revature.repos.reimbursment_dao;

@Service
public class ReimbursementService {
	@Autowired
	reimbursment_dao reimbursementDao;
	
	public int CreateReimbursement(String description, float amount, int statusId, int statusTypeId, int authorId)
	{
		ers_reimbursement reimbursement = null;
		
		Timestamp today = new Timestamp(System.currentTimeMillis());
		
		reimbursement = new ers_reimbursement();
		reimbursement.setReimb_author(authorId);
		reimbursement.setReimb_status_id(statusId);
		reimbursement.setReimb_status_type_id(statusTypeId);
		reimbursement.setReimp_description(description);
		reimbursement.setReimp_submitted(today);
		reimbursement.setReimb_amount(amount);
	
		reimbursementDao.save(reimbursement);
		
		return  reimbursement.getReimp_id();
	}
	
	
	public ArrayList<ers_reimbursement> getAllTicketsByUserId(int userId)
	{
		 return reimbursementDao.getAllTicketsByUserId(userId);
	}
	
	public ArrayList<ers_reimbursement> getAllTicketsByStatus()
	{
		return reimbursementDao.getAllTicketsByStatus();
	}
}
