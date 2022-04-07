package com.opendog.opendogserver.service.serviceImpl;

import com.opendog.opendogserver.entity.Case;
import com.opendog.opendogserver.service.CaseService;
import com.opendog.opendogserver.utils.CaseSelectCondition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseServiceImpl implements CaseService {


    @Override
    public boolean insertCase(Case icase) {
        return false;
    }

    @Override
    public boolean deleteCase(List<Case> cases) {
        return false;
    }

    @Override
    public Case updateCase(Case icase) {
        return null;
    }

    @Override
    public List<Case> selectCase(CaseSelectCondition caseSelectCondition) {
        return null;
    }

    @Override
    public List<Case> selectCase(int uid, int cid) {
        return null;
    }

    @Override
    public List<Case> selectCase(int uid) {
        return null;
    }

    @Override
    public Case getCaseWithPasswd(int cid, String passwd) {
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
