package com.opendog.opendogserver.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;


@ToString
@Getter
@Setter
public class User implements Serializable {
    private int uid;
    private String username;
    private String password;
    private String email;
    private String question;
    private String answer;
    private int role;
    private Timestamp created_time;
    private Timestamp update_time;
}
