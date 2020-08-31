package com.example.demo.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "users")
@NamedQuery(
        name="counters",
        query="SELECT COUNT(*) FROM users"
        )
public class Users {

    @Id
    @Column(unique = true)
    @NotBlank
    private String login_id;

    @NotBlank
    private String password;
    
    @NotBlank
    private String name;
    
    private Integer admin_flag;
    private Integer delete_flag;
}
