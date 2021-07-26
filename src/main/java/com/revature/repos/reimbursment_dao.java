package com.revature.repos;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.ers_reimbursement;

@Repository
public interface reimbursment_dao extends CrudRepository<ers_reimbursement, Long> {   
	@Query("from ers_reimbursement where reimb_author = ?1")
	public ArrayList<ers_reimbursement> getAllTicketsByUserId(int userId);
	
	@Query("from ers_reimbursement") // INNER JOIN ers_reimbursement_status on ers_reimbursement_status.reimb_status_id = ers_reimbursement.reimb_status_type_id and ers_reimbursement_status.reimb_status = ?1")
	public ArrayList<ers_reimbursement> getAllTicketsByStatus();
	
    @Transactional
    @Modifying
    @Query("UPDATE ers_reimbursement set reimb_resolver = ?2,reimb_resolved = ?3, reimb_status_type_id = ?4  where reimb_id = ?1")
    public void updateTicketStatus(int ticketId, int author, Timestamp submitted, int newStatus);
}
