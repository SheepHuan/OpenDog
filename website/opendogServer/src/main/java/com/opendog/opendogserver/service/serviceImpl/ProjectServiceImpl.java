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
        //插入前,检查该用户是否有同名 project
        Map<String,Object> map = new HashMap<>();
        map.put("uid",project.getUid());
        map.put("project_name",project.getProjectName());
        List<Project> selected = selectProjectByMap(map);
        if (selected!=null)
            return null;

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

        //检查是否有同名
        Map<String,Object> map = new HashMap<>();
        map.put("projectName",project.getProjectName());
        map.put("uid",project.getUid());
        List<Project> sameNameProjects = projectMapper.selectByMap(map);
        boolean haveSameNameProject = false;
        for (Project sameNameProject: sameNameProjects){
            if (sameNameProject.getPid() != project.getPid()){
                haveSameNameProject = true;
            }
        }
        if (!haveSameNameProject){
            project.setUpdatedTime(new Date(System.currentTimeMillis()));
                projectMapper.updateById(project);
                return project;

        }else{
            return null;
        }



    }

    @Override
    public Project updateProjectTaskID(int pid, int tid) {
        Project project = selectProjetByPid(pid);
        if (project !=null){
            project.setTid(tid);
            return updateProject(project);
        }else{
            return null;
        }

    }

    @Override
    public Project updateProjectName(int pid, String projectName) {
        Project project = selectProjetByPid(pid);
        if (project !=null){
            project.setProjectName(projectName);
            return updateProject(project);
        }else{
            return null;
        }

    }

    @Override
    public Project updateProjectComment(int pid, String comment) {
        Project project = selectProjetByPid(pid);
        if (project !=null){
            project.setComment(comment);
            return updateProject(project);
        }else{
            return null;
        }
    }

    @Override
    public Project updateProjectNameAndComment(int pid, String projectName, String comment) {
        Project project = selectProjetByPid(pid);
        if (project !=null){
            project.setProjectName(projectName);
            project.setComment(comment);
            return updateProject(project);
        }else{
            return null;
        }
    }

    @Override
    public Project updateProjectNameAndTid(int pid, String projectName, int tid) {
        Project project = selectProjetByPid(pid);
        if (project !=null){
            project.setProjectName(projectName);
            project.setTid(tid);
            return updateProject(project);
        }else{
            return null;
        }
    }

    @Override
    public Project updateProjectCommentAndTid(int pid, String comment, int tid) {
        Project project = selectProjetByPid(pid);
        if (project !=null){
            project.setComment(comment);
            project.setTid(tid);
            return updateProject(project);
        }else{
            return null;
        }
    }

    @Override
    public Project updateProjectNameAndCommentAndTid(int pid, String projectName, String comment, int tid) {
        Project project = selectProjetByPid(pid);
        if (project !=null){
            project.setComment(comment);
            project.setProjectName(projectName);
            project.setTid(tid);
            return updateProject(project);
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

    @Override
    public List<Project> selectProjectByMap(Map<String, Object> map) {
        List<Project> projectList=projectMapper.selectByMap(map);
        if (projectList.size()!=0)
            return projectList;
        else
            return null;
    }
}
