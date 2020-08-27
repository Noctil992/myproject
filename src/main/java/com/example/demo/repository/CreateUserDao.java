package com.example.demo.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Users;

@Repository
public class CreateUserDao {
    @Autowired
    EntityManager em;
    UsersRepository usersrepository;
    
    public Integer countUserCheck(String login_id) {
        
        String query = "";
        query += "SELECT COUNT(u) ";
        query += "FROM users ";
        query += "AS u ";
        query += "WHERE u.login_id = :login_id";
        
        return (Integer)em.createNativeQuery(query, Users.class).setParameter("login_id", login_id).getSingleResult();
            
    }
}
