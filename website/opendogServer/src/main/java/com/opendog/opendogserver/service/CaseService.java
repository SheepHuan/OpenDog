package com.opendog.opendogserver.service;

import com.opendog.opendogserver.entity.Case;
import com.opendog.opendogserver.utils.CaseSelectCondition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CaseService {


    /*
     * @Author opendog
     * @Description
     * 向数据库中插入一个 Case记录，返回是否插入成功
     * @Date 2022/4/7 19:53
     * @Param [icase]
     * @return boolean
     **/
    boolean insertCase(Case icase);

    /*
     * @Author opendog
     * @Description
     * 数据库中删除一条Case记录
     * @Date 2022/4/7 19:54
     * @Param [cases]
     * @return boolean
     **/
    boolean deleteCase(List<Case> cases);

    /*
     * @Author opendog
     * @Description
     * 更新一条Case记录
     * @Date 2022/4/7 19:54
     * @Param [icase]
     * @return com.opendog.opendogserver.entity.Case
     **/
    Case updateCase(Case icase);

    /*
     * @Author opendog
     * @Description
     * 根据指定查询条件获取Case记录
     * 暂不实现
     * @Date 2022/4/7 19:54
     * @Param [caseSelectCondition]
     * @return java.util.List<com.opendog.opendogserver.entity.Case>
     **/
    List<Case> selectCase(CaseSelectCondition caseSelectCondition);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/7 19:55
     * @Param [uid, cid]
     * @return java.util.List<com.opendog.opendogserver.entity.Case>
     **/
    Case selectCase(int uid,int cid);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/7 19:55
     * @Param [uid]
     * @return java.util.List<com.opendog.opendogserver.entity.Case>
     **/
    List<Case> selectCase(int uid);
    
    /*
     * @Author opendog
     * @Description 

     * @Date 2022/4/9 0:48

     * @Date 2022/4/9 9:55

     * @Param [pid]
     * @return java.util.List<com.opendog.opendogserver.entity.Case>
     **/
    List<Case> selectCasesByPid(int pid);

    
    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/9 11:16
     * @Param [tid]
     * @return java.util.List<com.opendog.opendogserver.entity.Case>
     **/
    List<Case> selectCaseByTid(int tid);


    /*
     * @Author opendog
     * @Description
     * 通过密码获取Case详细信息
     * @Date 2022/4/7 19:56
     * @Param [uid, passwd]
     * @return com.opendog.opendogserver.entity.Case
     **/
    Case getCaseWithPasswd(int cid, String passwd);

    /*
     * @Author opendog
     * @Description
     * 归档，将Case划分到Project下面
     * @Date 2022/4/7 19:57
     * @Param [cid, pid]
     * @return boolean
     **/
    boolean pigeonholeCase(int cid,int pid);

    /*
     * @Author opendog
     * @Description
     * 分享Case，暂不实现
     * @Date 2022/4/7 19:57
     * @Param [uid, cid]
     * @return java.lang.String
     **/
    String shareCase(int uid,int cid);
}
