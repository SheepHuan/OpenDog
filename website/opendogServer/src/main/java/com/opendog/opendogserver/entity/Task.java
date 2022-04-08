package com.opendog.opendogserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * @className: SharedTask
 * @description: 任务类
 * @author: opendog
 * @date: 2022/4/8
 **/
@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("TB_TASK")
public class Task {

    @TableId(value = "tid",type = IdType.AUTO)
    Integer tid;

    @TableField(value = "uid")
    Integer uid;

    @TableField(value = "task_name")
    String taskName;

    @TableField(value = "comment")
    String comment;

    @TableField(value = "updated_time")
    Date updatedTime;

    @TableField(value = "created_time")
    Date createdTime;
}
