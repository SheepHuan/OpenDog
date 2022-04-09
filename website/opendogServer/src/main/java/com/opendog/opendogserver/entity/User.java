package com.opendog.opendogserver.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @className: SharedTask
 * @description: 用户类
 * @author: opendog
 * @date: 2022/4/8
 **/
@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("TB_USER")
public class User implements Serializable {

    //主键自增
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "passwd")
    private String password;

    @TableField(value = "email")
    private String email;

    @TableField(value = "question")
    private String question;

    @TableField(value = "answer")
    private String answer;


    @TableField(value = "role")
    private Integer role;

    @TableField(value = "created_time")
    private LocalDateTime createdTime;

    @TableField(value = "updated_time")
    private LocalDateTime updatedTime;


}
