package com.revature.repos;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.ers_user;

@Repository
public interface user_dao extends CrudRepository<ers_user, Long> {
    @Query("from ers_user")
    public ArrayList<ers_user> findByUsername(String username);
}
