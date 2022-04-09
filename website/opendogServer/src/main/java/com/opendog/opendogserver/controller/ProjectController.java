package com.opendog.opendogserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.opendog.opendogserver.entity.Case;
import com.opendog.opendogserver.entity.Project;
import com.opendog.opendogserver.service.CaseService;
import com.opendog.opendogserver.service.ProjectService;
import com.opendog.opendogserver.service.TokenService;
import com.opendog.opendogserver.utils.IOUtils;
import com.opendog.opendogserver.utils.MHttpHeader;
import com.opendog.opendogserver.utils.RetJson;
import com.opendog.opendogserver.utils.RetState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project/")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    CaseService caseService;

    @Autowired
    TokenService tokenService;

    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/8 17:39
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "create_project")
    public RetJson createProject(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "创建成功";
        Map<String,Project> data =new HashMap<>();

        try{
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();
            //将json转为JSONObject
            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            String projectName = params.getString("projectName");
            String comment = params.getString("comment");
            if (tokenService.checkTokenIsValid(uid,token)){
                Project project = new Project();
                project.setUid(uid);
                project.setProjectName(projectName);
                project.setComment(comment);
                project =  projectService.insertProject(project);
                if ( project!=null ){
                    state = RetState.SUCCESS;
                    msg = "成功";
                    data.put("project",project);
                }else {
                    state = RetState.ERROR;
                    msg = "数据库操作异常";
                }

            }else{
                state = RetState.ERROR;
                msg = "授权不通过";
            }

        }catch (Exception e){
            e.printStackTrace();
            state = RetState.ERROR;
            msg = "参数异常";
        }

        return  RetJson.retJson(state,msg,data);
    }

    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/8 17:39
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "delete_project")
    public RetJson deleteProject(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "完全删除";
        Map<String,List<Integer>> data =new HashMap<>();
        List<Integer> isUndeleted = new ArrayList<>();
        data.put("failure",isUndeleted);
        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();
            //将json转为JSONObject
            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            Integer[] projectIds =  params.getJSONArray("projectId").toArray(new Integer[ params.getJSONArray("projectId").size()]);

            if (tokenService.checkTokenIsValid(uid,token)){
                for (Integer projectId : projectIds) {
                    if (!projectService.deleteProject(projectId)) {
                        state = RetState.ERROR;
                        msg = "未完全删除成功";
                        isUndeleted.add(projectId);
                    }
                }
                if (isUndeleted.size()>0)
                    data.put("failure",isUndeleted);
            }else{
                state = RetState.ERROR;
                msg = "授权不通过";
            }

        }catch (Exception e){
            e.printStackTrace();
            state = RetState.ERROR;
            msg = "参数异常";
        }

        return  RetJson.retJson(state,msg,data);
    }


    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/8 17:39
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "update_project")
    public RetJson updateProject(HttpServletRequest request){

        RetState state = RetState.SUCCESS;
        String msg = "更新成功";
        Map<String,Project> data =new HashMap<>();

        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            //将RequestBody转为JSONObject
            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            int pid = params.getInteger("pid");
            int tid = params.getInteger("tid");
            String projectName = params.getString("projectName");
            String comment = params.getString("comment");
            if (tokenService.checkTokenIsValid(uid,token))
            {
                //检查PID是否合法
                Project project = projectService.selectProjetByPid(pid);
                if (project != null){
                    if (tid > 0)
                        project.setTid(tid);
                    if (!projectName.equals(""))
                        project.setProjectName(projectName);
                    if (!comment.equals(""))
                        project.setComment(comment);
                    project = projectService.updateProject(project);
                    if (project != null){
                        state = RetState.SUCCESS;
                        msg = "记录更新成功";
                        data.put("project",project);
                    }else{
                        state = RetState.ERROR;
                        msg = "记录更新失败";
                    }
                }else{
                    state = RetState.ERROR;
                    msg = "pid 不合法";
                }
            }else{
                state = RetState.ERROR;
                msg = "授权不合法";
            }

        }catch (Exception e){
            e.printStackTrace();
            state = RetState.ERROR;
            msg = "失败";
        }


        return RetJson.retJson(state,msg,data);
    }

    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/9 0:41
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "select_project_case")
    public RetJson selectProjectCase(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "更新成功";
        Map<String,List<Case>> data =new HashMap<>();
        data.put("caseList",new ArrayList<>());
        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            int pid = params.getInteger("pid");
            if (tokenService.checkTokenIsValid(uid,token)){
                //TODO 这里仅简单实现了查询，没有对应的权限检查
                //TODO 1. uid 是否是project owner
                //TODO 2. uid 是否参与了project owner共同的task
                Project project=projectService.selectProjetByPid(pid);
                if (project!=null){
                    List<Case> cases = caseService.selectCasesByPid(pid);
                    if (cases!=null){
                        data.put("caseList",cases);
                    }
                }else{
                    state = RetState.ERROR;
                    msg = "该项目不存在";
                }
            }else{
                state = RetState.ERROR;
                msg = "授权不合法";
            }


        }catch (Exception e){
            e.printStackTrace();
            state = RetState.ERROR;
            msg = "参数异常";
        }
        
        return RetJson.retJson(state,msg,data);
    }
    
    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/8 17:39
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "select_user_projects")
    public RetJson selectUserProjects(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "更新成功";
        Map<String,List<Project>> data =new HashMap<>();
        data.put("projectList",new ArrayList<>());
        try{
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            if (tokenService.checkTokenIsValid(uid,token)){
                List<Project> projectList = projectService.selectProjectByUid(uid);
                if (projectList!=null){
                    //TODO 没有查询共享的task的中的project
                    List<Project> projectLists = projectService.selectProjectByUid(uid);
                    data.put("projectList",projectLists);
                }else{
                    state = RetState.ERROR;
                    msg = "该ID无项目";
                }

            }else{
                state = RetState.ERROR;
                msg = "授权不合法";
            }

        }catch (Exception e){
            e.printStackTrace();
            state = RetState.ERROR;
            msg = "参数异常";
        }
        return RetJson.retJson(state,msg,data);
    }
    
    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/8 17:39
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "get_project_detail")
    public RetJson getProjectDetail(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "更新成功";
        Map<String,List<Object>> data =new HashMap<>();
        data.put("failure",new ArrayList<>());
        data.put("success",new ArrayList<>());
        try{
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            Integer[] projectIds =  params.getJSONArray("pid").toArray(new Integer[ params.getJSONArray("pid").size()]);
            List<Object> isUnselected = new ArrayList<>();
            List<Object> isSelected = new ArrayList<>();
            if (tokenService.checkTokenIsValid(uid,token)){

                for (int pid : projectIds) {
                    Project project = projectService.selectProjetByPid(pid);
                    if (project == null) {

                        isUnselected.add(pid);
                    }else{
                        isSelected.add(project);
                    }
                }
                if (isUnselected.size()==0){
                    state = RetState.SUCCESS;
                    msg = "全部查询成功";
                    data.put("success",isSelected);
                }else{
                    state = RetState.SUCCESS;
                    msg = "未查询成功";
                    data.put("success",isSelected);
                    data.put("failure",isUnselected);
                }

            }else{
                state = RetState.ERROR;
                msg = "授权不合法";
            }

        }catch (Exception e){
            e.printStackTrace();
            state = RetState.ERROR;
            msg = "参数异常";
        }
        return RetJson.retJson(state,msg,data);
    }

}
