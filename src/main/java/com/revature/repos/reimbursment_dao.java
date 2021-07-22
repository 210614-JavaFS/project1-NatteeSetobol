package com.revature.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.ers_reimbursement;

@Repository
public interface reimbursment_dao extends CrudRepository<ers_reimbursement, Long> {   
	

}
