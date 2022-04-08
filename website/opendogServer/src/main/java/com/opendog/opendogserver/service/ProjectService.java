package com.opendog.opendogserver.service;

import com.opendog.opendogserver.entity.Project;
import org.springframework.stereotype.Service;

import java.util.List;

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
    Project updateProjectComment(int pid, String Comment);

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


}
