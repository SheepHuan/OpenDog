package com.opendog.opendogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.opendog.opendogserver.entity.User;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {

}
