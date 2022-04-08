package com.opendog.opendogserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @className: SharedTask
 * @description: 项目类
 * @author: opendog
 * @date: 2022/4/8
 **/
@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("TB_PROJECT")
public class Project {

    @TableId(value = "pid", type = IdType.AUTO)
    int pid;

    @TableField(value = "project_name")
    String projectName;

    @TableField(value = "uid")
    int uid;

    @TableField(value = "tid")
    int tid;

    @TableField(value = "comment")
    String comment;

}


