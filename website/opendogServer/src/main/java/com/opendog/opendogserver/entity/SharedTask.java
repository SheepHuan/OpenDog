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
@TableName("TB_SHARED_TASK")
public class SharedTask
{
    @TableId(value = "tsid",type = IdType.AUTO)
    Integer tsid;

    @TableField(value = "tid")
    Integer tid;

    @TableField(value = "uid")
    Integer uid;

}
