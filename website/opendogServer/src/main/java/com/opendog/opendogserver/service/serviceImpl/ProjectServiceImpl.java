package com.opendog.opendogserver.service.serviceImpl;

import com.opendog.opendogserver.entity.Project;
import com.opendog.opendogserver.mapper.ProjectMapper;
import com.opendog.opendogserver.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectMapper projectMapper;

    @Override
    public Project insertProject(Project project) {
        project.setCreatedTime(new Date(System.currentTimeMillis()));
        project.setUpdatedTime(new Date(System.currentTimeMillis()));
        try{
            projectMapper.insert(project);
            return project;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public Project updateProject(Project project) {
        project.setUpdatedTime(new Date(System.currentTimeMillis()));
        try{
            projectMapper.updateById(project);

            return project;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Project updateProjectName(int pid, String projectName) {
        Project project = selectProjetByPid(pid);
        if (project !=null){
            project.setProjectName(projectName);
            projectMapper.updateById(project);
            return project;
        }else{
            return null;
        }

    }

    @Override
    public Project updateProjectComment(int pid, String comment) {
        Project project = selectProjetByPid(pid);
        if (project !=null){
            project.setComment(comment);
            projectMapper.updateById(project);
            return project;
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteProject(int pid) {
        Project project = projectMapper.selectById(pid);
        if (project!=null){
            projectMapper.deleteById(project);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Project> selectProjectByUid(int uid) {
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        List<Project> projectList=projectMapper.selectByMap(map);
        if (projectList.size()!=0)
            return projectList;
        else
            return null;
    }

    @Override
    public List<Project> selectProjectByTid(int tid) {
        Map<String,Object> map = new HashMap<>();
        map.put("tid",tid);
        List<Project> projectList=projectMapper.selectByMap(map);
        if (projectList.size()!=0)
            return projectList;
        else
            return null;
    }

    @Override
    public Project selectProjetByPid(int pid) {
        return projectMapper.selectById(pid);
    }
}
