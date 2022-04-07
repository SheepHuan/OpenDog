package com.opendog.opendogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.opendog.opendogserver.entity.Case;
import com.opendog.opendogserver.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CaseMapper extends BaseMapper<Case> {

}
