package com.revature.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.ers_reimbursment_type;

@Repository
public interface reimbursment_type_dao extends CrudRepository<ers_reimbursment_type, Integer>{
	@Query("from ers_reimbursment_type where reimb_type_id = ?1")
	public ArrayList<ers_reimbursment_type> FindAllReimbursmentType(int id);
	
}
