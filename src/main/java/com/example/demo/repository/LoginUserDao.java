package com.example.demo.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Users;

@Repository
public class LoginUserDao {
    @Autowired
    EntityManager em;
    UsersRepository usersrepository;
    
    public Users findUser(String login_id) {
        
        
        String query = "";
        query += "SELECT * ";
        query += "FROM users ";
        query += "WHERE login_id = :login_id";

        //EntityManagerで取得された結果はオブジェクトとなるので、LoginUser型へキャストが必要となる
        return (Users)em.createNativeQuery(query, Users.class).setParameter("login_id", login_id).getSingleResult();
    }
}
