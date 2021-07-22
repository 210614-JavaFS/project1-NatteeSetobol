package com.revature.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.ers_reimbursement_status;

@Repository
public interface reimbursment_status_dao extends CrudRepository<ers_reimbursement_status, Integer> {

}
