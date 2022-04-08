package com.opendog.opendogserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @className: SharedTask
 * @description: 分享类
 * @author: opendog
 * @date: 2022/4/8
 **/


@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("TB_TASK_SHARED")
public class SharedTask
{
    @TableId(value = "tsid",type = IdType.AUTO)
    int tsid;

    @TableField(value = "tid")
    int tid;

    @TableField(value = "uid")
    int uid;

}
