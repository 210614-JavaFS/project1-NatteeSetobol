package com.revature.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.ers_reimbursement_status;

@Repository
public interface reimbursment_status_dao extends CrudRepository<ers_reimbursement_status, Integer> {
	@Query("from ers_reimbursement_status where reimb_status_id = ?1")
	ArrayList<ers_reimbursement_status> getAllStatusById(int id);
}
