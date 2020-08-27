package com.example.demo.ant;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.demo.models.Users;

@Repository
public class UsersService{
    private EntityManager entityManager;
    
    public List<Users> finder(String login_id) {
    List<Users> list = entityManager
            .createQuery("SELECT * from users where login_id = :login_id")
            .setParameter("login_id", login_id)
            .getResultList();
    
        return list;
    }
}
