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
    int caseId;

    @TableField(value = "pid")
    int projectid;

    @TableField(value = "tid")
    int taskid;

    @TableField(value = "did")
    String dataId;

    @TableField(value = "uid")
    int userId;

    @TableField(value = "case_name")
    String caseName;

    //不为空，默认填写0
    @TableField(value = "access_state")
    int accessState;

    @TableField(value = "access_passwd")
    String accessPasswd;

    @TableField(value = "comment")
    String comment;

    @TableField(value = "updated_time")
    Date updatedTime;

    @TableField(value = "created_time")
    Date createdTime;
}
