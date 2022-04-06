package com.opendog.opendogserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.sql.Date;

@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_token")
public class Token {

    @TableId(value = "id",type = IdType.AUTO)
    private int tokenId;

    @TableField(value = "uid")
    private int userid;

    @TableField(value = "token")
    private String token;

    @TableField(value = "valid_time")
    private Date validTime;

}
