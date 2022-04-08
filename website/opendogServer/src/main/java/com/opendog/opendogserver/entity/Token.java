package com.opendog.opendogserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * @className: SharedTask
 * @description: 授权码类
 * @author: opendog
 * @date: 2022/4/8
 **/
@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("TB_TOKEN")
public class Token {

    @TableId(value = "id",type = IdType.AUTO)
    private int tokenId;

    @TableField(value = "uid")
    private int userId;

    @TableField(value = "token")
    private String token;

    @TableField(value = "valid_time")
    private Date validTime;

    @TableField(value = "state")
    private int state;

}
