package com.opendog.opendogserver.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @className: SharedTask
 * @description: 用例类
 * @author: opendog
 * @date: 2022/4/8
 **/
@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("TB_CASE")
public class Case {

    @TableId(value = "cid",type = IdType.AUTO)
    Integer caseId;

    @TableField(value = "pid")
    Integer projectid;

    @TableField(value = "tid")
    Integer taskid;

    @TableField(value = "did")
    String dataId;

    @TableField(value = "uid")
    Integer userId;

    @TableField(value = "case_name")
    String caseName;

    //不为空，默认填写0，填1表示要输入密码
    @TableField(value = "access_state")
    Integer accessState;

    @TableField(value = "access_passwd")
    String accessPasswd;

    //这个具有唯一性,用cid
    @TableField(value = "access_url")
    String accessUrl;

    @TableField(value = "comment")
    String comment;

    @TableField(value = "updated_time")
    Date updatedTime;

    @TableField(value = "created_time")
    Date createdTime;
}
