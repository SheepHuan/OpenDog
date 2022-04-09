package com.opendog.opendogserver.service;

import com.opendog.opendogserver.entity.Project;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ProjectService {

    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/8 19:08
     * @Param [project]
     * @return boolean
     **/
    Project insertProject(Project project);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/8 19:41
     * @Param [project]
     * @return com.opendog.opendogserver.entity.Project
     **/
    Project updateProject(Project project);
    
    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/8 20:35
     * @Param [tid]
     * @return com.opendog.opendogserver.entity.Project
     **/
    Project updateProjectTaskID(int pid,int tid);
    
    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/8 19:08
     * @Param [pid, projectName]
     * @return com.opendog.opendogserver.entity.Project
     **/
    Project updateProjectName(int pid, String projectName);

    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/8 19:08
     * @Param [pid, Comment]
     * @return com.opendog.opendogserver.entity.Project
     **/
    Project updateProjectComment(int pid, String comment);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/8 21:35
     * @Param [pid, projectName, comment]
     * @return com.opendog.opendogserver.entity.Project
     **/
    Project updateProjectNameAndComment(int pid,String projectName, String comment);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/8 21:35
     * @Param [pid, projectName, tid]
     * @return com.opendog.opendogserver.entity.Project
     **/
    Project updateProjectNameAndTid(int pid,String projectName, int tid);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/8 21:35
     * @Param [pid, comment, tid]
     * @return com.opendog.opendogserver.entity.Project
     **/
    Project updateProjectCommentAndTid(int pid,String comment, int tid);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/8 21:36
     * @Param [pid, projectName, comment, tid]
     * @return com.opendog.opendogserver.entity.Project
     **/
    Project updateProjectNameAndCommentAndTid(int pid,String projectName,String comment, int tid);

    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/8 19:08
     * @Param [pid]
     * @return boolean
     **/
    boolean deleteProject(int pid);

    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/8 19:08
     * @Param [uid]
     * @return java.util.List<com.opendog.opendogserver.entity.Project>
     **/
    List<Project> selectProjectByUid(int uid);

    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/8 19:24
     * @Param [tid]
     * @return java.util.List<com.opendog.opendogserver.entity.Project>
     **/
    List<Project> selectProjectByTid(int tid);

   /*
    * @Author opendog
    * @Description
    * @Date 2022/4/8 19:46
    * @Param [pid]
    * @return com.opendog.opendogserver.entity.Project
    **/
    Project selectProjetByPid(int pid);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/8 21:04
     * @Param [map]
     * @return java.util.List<com.opendog.opendogserver.entity.Project>
     **/
    List<Project> selectProjectByMap(Map<String,Object> map);
}
