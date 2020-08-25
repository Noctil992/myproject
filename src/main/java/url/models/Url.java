package url.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;


import lombok.Data;
import user.models.Users;

@Data
@Entity(name = "url")
public class Url {
    @Id
    @GeneratedValue
    private Integer id;
    
    private String title;
    
    private String url;
    
    @Lob
    private String content;
    
    private Date create_day;
    
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;
}
