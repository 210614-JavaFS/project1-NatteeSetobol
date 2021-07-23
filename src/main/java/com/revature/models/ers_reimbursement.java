package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ers_reimbursement")
public class ers_reimbursement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int reimb_id;
	Timestamp reimb_submitted;
	Timestamp reimb_resolved ;
	String reimb_description;
	float reimb_amount;
	byte[] reimb_receipt;
	int reimb_author;
	int reimb_resolver;
	public float getReimb_amount() {
		return reimb_amount;
	}
	public void setReimb_amount(float reimb_amount) {
		this.reimb_amount = reimb_amount;
	}
	int reimb_status_id;
	int reimb_status_type_id;
	public int getReimp_id() {
		return reimb_id;
	}
	public void setReimp_id(int reimp_id) {
		this.reimb_id = reimp_id;
	}
	public Timestamp getReimp_submitted() {
		return reimb_submitted;
	}
	public void setReimp_submitted(Timestamp reimp_submitted) {
		this.reimb_submitted = reimp_submitted;
	}
	public Timestamp getReimp_resolved() {
		return reimb_resolved;
	}
	public void setReimp_resolved(Timestamp reimp_resolved) {
		this.reimb_resolved = reimp_resolved;
	}
	public String getReimp_description() {
		return reimb_description;
	}
	public void setReimp_description(String reimp_description) {
		this.reimb_description = reimp_description;
	}
	public byte[] getReimb_receipt() {
		return reimb_receipt;
	}
	public void setReimb_receipt(byte[] reimb_receipt) {
		this.reimb_receipt = reimb_receipt;
	}
	public int getReimb_author() {
		return reimb_author;
	}
	public void setReimb_author(int reimb_author) {
		this.reimb_author = reimb_author;
	}
	public int getReimb_resolver() {
		return reimb_resolver;
	}
	public void setReimb_resolver(int reimb_resolver) {
		this.reimb_resolver = reimb_resolver;
	}
	public int getReimb_status_id() {
		return reimb_status_id;
	}
	public void setReimb_status_id(int reimb_status_id) {
		this.reimb_status_id = reimb_status_id;
	}
	public int getReimb_status_type_id() {
		return reimb_status_type_id;
	}
	public void setReimb_status_type_id(int reimb_status_type_id) {
		this.reimb_status_type_id = reimb_status_type_id;
	}
}
