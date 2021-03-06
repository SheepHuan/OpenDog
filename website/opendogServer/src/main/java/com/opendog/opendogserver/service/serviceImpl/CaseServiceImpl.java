package com.opendog.opendogserver.service.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.opendog.opendogserver.entity.Case;
import com.opendog.opendogserver.mapper.CaseMapper;
import com.opendog.opendogserver.service.CaseService;
import com.opendog.opendogserver.utils.CaseSelectCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

import java.util.Date;
import java.util.List;


@Service
public class CaseServiceImpl implements CaseService {
    @Autowired
    CaseMapper caseMapper;

    @Override
    public boolean insertCase(Case icase) {
        try{
            //Case 更新前保证Uid下无改名字
            Case sameCaseName = selectCaseByName(icase.getUserId(),icase.getCaseName());
            if (sameCaseName!=null)
                return false;
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
    public boolean deleteCase(int caseId) {

        //构建一个查询的wrapper
        Case icase = selectCase(caseId);
        try{
            if(icase != null){
                caseMapper.deleteById(icase);
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
            caseMapper.updateById(icase);
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
    public Case selectCase(int cid) {

       return caseMapper.selectById(cid);
    }

    @Override
    public List<Case> selectCaseByUid(int uid) {

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

        return null;
    }

    @Override
    public List<Case> selectCaseByTid(int tid) {
        Map <String,Object> map = new HashMap<>();
        map.put("tid",tid);
        List<Case> cases = caseMapper.selectByMap(map);
        if (cases.size()>0)
            return cases;
        return null;
    }

    @Override
    public Case selectCaseByName(int uid, String caseName) {
        Map <String,Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("case_name",caseName);
        List<Case> cases = caseMapper.selectByMap(map);
        if (cases.size()>0)
            return cases.get(0);
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
