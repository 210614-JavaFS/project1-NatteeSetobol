package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.ers_reimbursment_type;
import com.revature.repos.reimbursment_type_dao;

@Service
public class ReimbursementTypeServices {
	@Autowired
	reimbursment_type_dao reimbursmentTypeRepo;
	
	public int createReimbursmentType(String type)
	{
		int result = -1;
		ers_reimbursment_type reimbursmentType;
		
		reimbursmentType = new ers_reimbursment_type();
		reimbursmentType.setReimb_type(type);
		reimbursmentTypeRepo.save(reimbursmentType);
		return result;
	}
}
