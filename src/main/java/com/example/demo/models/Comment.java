package com.example.demo.models;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "url_id", nullable = false)
    private Url url;
    
    @NotBlank
    private String comment;
    
    private Timestamp createTimes;
    
    
}
