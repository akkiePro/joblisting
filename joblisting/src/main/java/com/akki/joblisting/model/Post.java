package com.akki.joblisting.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "JobPost")
@Data
public class Post {

    private String profile;
    private String desc;
    private int exp;
    private String []techs;


}
