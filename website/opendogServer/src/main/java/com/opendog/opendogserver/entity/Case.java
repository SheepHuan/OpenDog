package com.opendog.opendogserver.entity;

import com.baomidou.mybatisplus.annotation.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@TableName("tb_case")
public class Case {

    @TableId(value = "cid",type = IdType.AUTO)
    int caseId;

    @TableField(value = "pid")
    int projectid;

    @TableField(value = "tid")
    int taskid;

    @TableField(value = "did")
    int dataId;

    @TableField(value = "uid")
    int userId;

    @TableField(value = "case_name")
    String caseName;

    @TableField(value = "access_state")
    int accessState;

    @TableField(value = "access_passwd")
    String accessPasswd;

    @TableField(value = "comment")
    String comment;

    @TableField(value = "updated_time",fill = FieldFill.INSERT)
    Date updatedTime;

    @TableField(value = "created_time",fill = FieldFill.UPDATE)
    Date createdTime;
}
