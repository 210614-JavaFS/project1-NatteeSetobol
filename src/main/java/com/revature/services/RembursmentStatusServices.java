package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.ers_reimbursement_status;
import com.revature.repos.reimbursment_status_dao;

@Service
public class RembursmentStatusServices {
	@Autowired 
	reimbursment_status_dao reimburseRepo; 
	
	public int createRebursementStatus(String status)
	{	
		ers_reimbursement_status newStatus = new ers_reimbursement_status();
		newStatus.setReimb_status(status);
		
		reimburseRepo.save(newStatus);
		
		return newStatus.getReimb_status_id();
	}
}
