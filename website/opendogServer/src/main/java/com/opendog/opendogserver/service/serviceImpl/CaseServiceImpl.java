package com.opendog.opendogserver.service.serviceImpl;

<<<<<<< HEAD
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
=======
>>>>>>> upstream/main
import com.opendog.opendogserver.entity.Case;
import com.opendog.opendogserver.mapper.CaseMapper;
import com.opendog.opendogserver.service.CaseService;
import com.opendog.opendogserver.utils.CaseSelectCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.*;
=======
import java.util.Date;
import java.util.List;
>>>>>>> upstream/main

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
<<<<<<< HEAD
        //构建一个查询的wrapper
        QueryWrapper<Case> wrapper = new QueryWrapper<>();
        caseMapper.selectList(wrapper);
        try{
            if(cases.size()!=0){
                caseMapper.delete(wrapper);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
=======
>>>>>>> upstream/main
        return false;
    }

    @Override
    public Case updateCase(Case icase) {
<<<<<<< HEAD
        icase.setCreatedTime(new Date(System.currentTimeMillis()));
        icase.setUpdatedTime(new Date(System.currentTimeMillis()));
        try{
            caseMapper.updateById(icase);
            return icase;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
=======
        return null;
>>>>>>> upstream/main
    }

    @Override
    public List<Case> selectCase(CaseSelectCondition caseSelectCondition) {
<<<<<<< HEAD
        Map<String,Object> map = new HashMap<>();
        map.put("caseSelectCondition",caseSelectCondition);
        List<Case> caseList=caseMapper.selectByMap(map);
        if (caseList.size()!=0)
            return caseList;
        else
            return null;
=======
        return null;
>>>>>>> upstream/main
    }

    @Override
    public List<Case> selectCase(int uid, int cid) {
<<<<<<< HEAD
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("cid",cid);
        List<Case> caseList=caseMapper.selectByMap(map);
        if (caseList.size()!=0)
            return caseList;
        else
            return null;
=======
        return null;
>>>>>>> upstream/main
    }

    @Override
    public List<Case> selectCase(int uid) {
<<<<<<< HEAD
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        List<Case> caseList=caseMapper.selectByMap(map);
        if (caseList.size()!=0)
            return caseList;
        else
            return null;
=======
        return null;
>>>>>>> upstream/main
    }

    @Override
    public List<Case> selectCasesByPid(int pid) {
<<<<<<< HEAD
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
        QueryWrapper<Case> wrapper=new QueryWrapper<>();
        wrapper.eq("cid",cid).eq("password",password);
        Case case1=caseMapper.selectOne(wrapper);
        try{
            if(case1!=null){
                return case1;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
=======
        return null;
    }

    @Override
    public List<Case> selectCaseByTid(int tid) {
        return null;
    }

    @Override
    public Case getCaseWithPasswd(int cid, String passwd) {
>>>>>>> upstream/main
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
