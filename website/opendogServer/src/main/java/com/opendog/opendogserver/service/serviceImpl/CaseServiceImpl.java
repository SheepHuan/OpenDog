package com.opendog.opendogserver.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.opendog.opendogserver.entity.Case;
import com.opendog.opendogserver.entity.Project;
import com.opendog.opendogserver.mapper.CaseMapper;
import com.opendog.opendogserver.service.CaseService;
import com.opendog.opendogserver.utils.CaseSelectCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CaseServiceImpl implements CaseService {
    @Autowired
    CaseMapper caseMapper;

    @Override
    public boolean insertCase(Case icase) {
        try{
            icase.setCreatedTime(new Date(System.currentTimeMillis()));
            icase.setUpdatedTime(new Date(System.currentTimeMillis()));
            caseMapper.insert(icase);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteCase(List<Case> cases) {
        //构建一个查询的wrapper
        QueryWrapper<Case> wrapper = new QueryWrapper<>();
        List<Case> list=caseMapper.selectList(wrapper);
        try{
            if(list!=null){
                caseMapper.delete(wrapper);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public Case updateCase(Case icase) {
        icase.setUpdatedTime(new Date(System.currentTimeMillis()));
        try{
            caseMapper.deleteById(icase);
            return icase;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Case> selectCase(CaseSelectCondition caseSelectCondition) {
        Map<String,Object> map = new HashMap<>();
        map.put("caseSelectCondition",caseSelectCondition);
        List<Case> caseList=caseMapper.selectByMap(map);
        if (caseList.size()!=0)
            return caseList;
        else
            return null;
    }

    @Override
    public List<Case> selectCase(int uid, int cid) {
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("cid",cid);
        List<Case> caseList=caseMapper.selectByMap(map);
        if (caseList.size()!=0)
            return caseList;
        else
            return null;
    }

    @Override
    public List<Case> selectCase(int uid) {
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        List<Case> caseList=caseMapper.selectByMap(map);
        if (caseList.size()!=0)
            return caseList;
        else
            return null;
    }

    @Override
    public List<Case> selectCasesByPid(int pid) {
        Map<String,Object> map = new HashMap<>();
        map.put("pid",pid);
        List<Case> caseList=caseMapper.selectByMap(map);
        if (caseList.size()!=0)
            return caseList;
        else
            return null;
    }

    @Override
    public Case getCaseWithPasswd(int cid, String password) {
        return null;
    }

    @Override
    public boolean pigeonholeCase(int cid, int pid) {
        return false;
    }

    @Override
    public String shareCase(int uid, int cid) {
        return null;
    }
}
