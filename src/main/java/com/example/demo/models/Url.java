package com.example.demo.models;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

import lombok.Data;


@Data
@Entity(name = "url")
public class Url {
    @Id
    @GeneratedValue
    private Integer id;
    
    @NotBlank
    private String title;
    
    @NotBlank
    private String urls;
    
    @Lob
    private String content;
    
    private Date create_day;
    
    private String loginUserId;
    
    public Integer public_flag;
}
