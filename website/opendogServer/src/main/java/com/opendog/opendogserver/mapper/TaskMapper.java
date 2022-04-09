package com.opendog.opendogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.opendog.opendogserver.entity.Task;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: TaskMapper
 * @description: TODO 类描述
 * @author: opendog
 * @date: 2022/4/8
 **/
@Mapper
public interface TaskMapper extends BaseMapper<Task> {
}
