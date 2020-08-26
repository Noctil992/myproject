package com.example.demo.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "users")
public class Users {
    @Id
    @GeneratedValue
    private Integer id;
    
    private String login_id;
    private String password;
    private String name;
    private Integer admin_flag;
    private Integer delete_flag;
}
