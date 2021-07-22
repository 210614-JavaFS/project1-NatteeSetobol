package com.revature.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.ers_reimbursment_type;

@Repository
public interface reimbursment_type_dao extends CrudRepository<ers_reimbursment_type, Integer>{

}
