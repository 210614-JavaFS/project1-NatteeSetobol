package com.revature.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.ers_users_roles;

@Repository
public interface user_roles_dao extends  CrudRepository<ers_users_roles, Long> {
	@Query("from ers_users_roles where ers_user_role_id = ?1")
	public ArrayList<ers_users_roles> findUserRoleByUserRoleId(long l);
}

