package com.opendog.opendogserver.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.opendog.opendogserver.entity.SharedTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @className: SharedTaskMapper
 * @description: TODO 类描述
 * @author: opendog
 * @date: 2022/4/8
 **/
@Mapper
public interface SharedTaskMapper extends BaseMapper<SharedTask> {

    @Select("SELECT uid FROM TB_SHARED_TASK WHERE tid=#{tid}")
    List<Integer> selectUidByTid(int tid);
}
