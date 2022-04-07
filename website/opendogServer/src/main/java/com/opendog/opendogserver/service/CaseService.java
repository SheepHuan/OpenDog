package com.opendog.opendogserver.service;

import com.opendog.opendogserver.entity.Case;
import com.opendog.opendogserver.utils.CaseSelectCondition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CaseService {



    Case insertCase();

    boolean deleteCase(List<Case> cases);

    Case updateCase(int cid, int uid,String caseName,String comment);

    List<Case> selectCase(CaseSelectCondition caseSelectCondition);

    List<Case> selectCase(int uid);

    Case selectCaseWithPasswd(int uid,String passwd);

    boolean pigeonholeCase(int cid,int pid);

    String shareCase(int uid,int cid);
}
