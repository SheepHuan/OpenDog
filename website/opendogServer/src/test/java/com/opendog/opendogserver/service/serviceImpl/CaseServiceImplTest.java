package com.opendog.opendogserver.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.opendog.opendogserver.entity.Case;
import com.opendog.opendogserver.mapper.CaseMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
@Service
@MapperScan
class CaseServiceImplTest {

    @Autowired
    CaseMapper caseMapper;

    @Test
    void selectCase() {
        QueryWrapper <Case> wrapper=new QueryWrapper<>();
        List<Case> list=caseMapper.selectList(wrapper);
        System.out.println(list);
    }
}