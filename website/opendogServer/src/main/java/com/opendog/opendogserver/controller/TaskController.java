package com.opendog.opendogserver.controller;


<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

=======
import com.alibaba.fastjson.JSONObject;
import com.opendog.opendogserver.entity.Case;
import com.opendog.opendogserver.entity.Project;
import com.opendog.opendogserver.entity.Task;
import com.opendog.opendogserver.entity.User;
import com.opendog.opendogserver.service.CaseService;
import com.opendog.opendogserver.service.ProjectService;
import com.opendog.opendogserver.service.TaskService;
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
import java.nio.file.FileAlreadyExistsException;
import java.util.*;

>>>>>>> upstream/main
@RestController
@RequestMapping("/task/")
public class TaskController {

<<<<<<< HEAD
=======
    @Autowired
    TokenService tokenService;

    @Autowired
    TaskService taskService;

    @Autowired
    ProjectService projectService;

    @Autowired
    CaseService caseService;



    @PostMapping(value = "create_task")
    public RetJson createTask(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        Map<String, Task> data =new HashMap<>();

        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            String taskName = params.getString("taskName");
            String comment = params.getString("comment");
            if (tokenService.checkTokenIsValid(uid,token)){
                Task task = new Task();
                task.setUid(uid);
                task.setTaskName(taskName);
                task.setComment(comment);

                task = taskService.insertTask(task);
                if (task!=null){

                    data.put("task",task);
                }else{
                    state = RetState.DATABASEERROR;
                }
            }else{
                state = RetState.UNAUTHORIZED;
            }


        }catch (Exception e){
            e.printStackTrace();
            state = RetState.BADPARAMS;
        }

        return RetJson.retJson(state,data);
    }


    @PostMapping(value = "delete_task")
    public RetJson deleteTask(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        Map<String, List<Integer>> data =new HashMap<>();
        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            Integer [] tids = params.getJSONArray("tid").toArray(new Integer[ params.getJSONArray("tid").size()]);
            List<Integer> failDeleted = new ArrayList<>();
            boolean isAllDeleted = true;
            if (tokenService.checkTokenIsValid(uid,token)){
                for (int tid: tids){
                    Task task = taskService.selectTaskByTid(tid);
                    if (task!=null){
                        if (taskService.deleteTask(tid)){

                            state= RetState.SUCCESS;
                        }else{
                            isAllDeleted = false;
                            failDeleted.add(tid);
                        }
                    }else{
                        isAllDeleted = false;
                        state = RetState.DATABASEERROR;
                        failDeleted.add(tid);
                    }
                }
                if (!isAllDeleted){
                    data.put("failure",failDeleted);
                }else{
                    data.put("failure",new ArrayList<>());
                }

            }else{
                state = RetState.UNAUTHORIZED;
            }


        }catch (Exception e){
            e.printStackTrace();
            state = RetState.BADPARAMS;
        }

        return RetJson.retJson(state,data);
    }

    @PostMapping(value = "update_task")
    public RetJson updateTask(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        Map<String, Task> data =new HashMap<>();
        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            int tid = params.getInteger("tid");
            String taskName = params.getString("taskName");
            String comment = params.getString("comment");
            if (tokenService.checkTokenIsValid(uid,token)){
                Task task = taskService.selectTaskByTid(tid);
                if (task!=null){
                    task.setTaskName(taskName);
                    task.setComment(comment);
                    task = taskService.updateTask(task);
                    if (task!=null){
                        data.put("task",task);
                    }else{
                        state  = RetState.DATABASEERROR;
                    }

                }else{
                    state  = RetState.DATABASEERROR;
                }


            }else{
                state = RetState.UNAUTHORIZED;
            }


        }catch (Exception e){
            e.printStackTrace();
            state = RetState.BADPARAMS;
        }

        return RetJson.retJson(state,data);
    }

    @PostMapping(value = "get_user_tasks")
    public RetJson getUserTask(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        Map<String, List<Task>> data =new HashMap<>();
        data.put("taskList",new ArrayList<>());
        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            if (tokenService.checkTokenIsValid(uid,token)){
                List<Task> tasks = taskService.selectTaskByUid(uid);
                data.put("taskList", Objects.requireNonNullElseGet(tasks, ArrayList::new));

            }else{
                state = RetState.UNAUTHORIZED;
            }


        }catch (Exception e){
            e.printStackTrace();
            state = RetState.BADPARAMS;
        }

        return RetJson.retJson(state,data);
    }

    @PostMapping(value = "get_task_projects")
    public RetJson getTaskProjects(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        Map<String, List<Project>> data =new HashMap<>();
        data.put("projectList",new ArrayList<>());
        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            int tid = params.getInteger("tid");
            if (tokenService.checkTokenIsValid(uid,token)){
                List<Project> projectList = projectService.selectProjectByTid(tid);
                data.put("projectList", Objects.requireNonNullElseGet(projectList, ArrayList::new));


            }else{
                state = RetState.UNAUTHORIZED;
            }


        }catch (Exception e){
            e.printStackTrace();
            state = RetState.BADPARAMS;
        }

        return RetJson.retJson(state,data);
    }

    @PostMapping(value = "get_task_cases")
    public RetJson getTaskCases(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        Map<String, List<Case>> data =new HashMap<>();
        data.put("casetList",new ArrayList<>());
        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            int tid = params.getInteger("tid");
            if (tokenService.checkTokenIsValid(uid,token)){
                List<Case> cases = caseService.selectCaseByTid(tid);
                data.put("caseList", Objects.requireNonNullElseGet(cases, ArrayList::new));

            }else{
                state = RetState.UNAUTHORIZED;
            }


        }catch (Exception e){
            e.printStackTrace();
            state = RetState.BADPARAMS;
        }

        return RetJson.retJson(state,data);
    }

    @PostMapping(value = "get_task_detail")
    public RetJson getTaskDeatil(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        Map<String, Task> data =new HashMap<>();

        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            int tid = params.getInteger("tid");
            if (tokenService.checkTokenIsValid(uid,token)){
                Task task= taskService.selectTaskByTid(tid);
                if (task!=null)
                {
                    data.put("task",task);
                }else {
                    state = RetState.DATABASEERROR;
                }

            }else{
                state = RetState.UNAUTHORIZED;
            }


        }catch (Exception e){
            e.printStackTrace();
            state = RetState.BADPARAMS;
        }

        return RetJson.retJson(state,data);
    }

    @PostMapping(value = "add_task_members")
    public RetJson addTaskMembers(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        Map<String, List<Integer>> data =new HashMap<>();
        data.put("failure",new ArrayList<>());
        data.put("success",new ArrayList<>());
        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            int tid = params.getInteger("tid");
            Integer[] uids =  params.getJSONArray("uid").toArray(new Integer[ params.getJSONArray("uid").size()]);
            if (tokenService.checkTokenIsValid(uid,token)){

                List<Integer> failAdded = new ArrayList<>();
                List<Integer> successAdded = new ArrayList<>();
                boolean[] isAdded = taskService.addUsersToTask(tid,uids);
                for (int i=0;i<isAdded.length;i++){
                    if (!isAdded[i])
                    {
                        failAdded.add(uids[i]);
                    }else{
                        successAdded.add(uids[i]);
                    }
                }
                data.put("failure",failAdded);
                data.put("success",successAdded);
            }else{
                state = RetState.UNAUTHORIZED;
            }


        }catch (Exception e){
            e.printStackTrace();
            state = RetState.BADPARAMS;
        }

        return RetJson.retJson(state,data);
    }


    @PostMapping(value = "select_task_members")
    public RetJson selectUidFromTask(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        Map<String, List<Integer>> data =new HashMap<>();
        data.put("uid",new ArrayList<>());
        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            int tid = params.getInteger("tid");
            if (tokenService.checkTokenIsValid(uid,token)){
               List<Integer> uids = taskService.selectUsersFromTask(tid);
                data.put("uid", Objects.requireNonNullElseGet(uids, ArrayList::new));

            }else{
                state = RetState.UNAUTHORIZED;
            }


        }catch (Exception e){
            e.printStackTrace();
            state = RetState.BADPARAMS;
        }

        return RetJson.retJson(state,data);
    }


    @PostMapping(value = "remove_task_members")
    public RetJson removeUidFromTask(HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        Map<String, List<Integer>> data =new HashMap<>();
        data.put("uid",new ArrayList<>());
        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            int tid = params.getInteger("tid");
            Integer[] uids =  params.getJSONArray("uid").toArray(new Integer[ params.getJSONArray("uid").size()]);
            if (tokenService.checkTokenIsValid(uid,token)){

                    boolean isAllDeleted = true;
                  boolean []isDeleted=  taskService.removeUsersFromTask(tid,uids);
                  List<Integer> failRemove =  new ArrayList<>();
                  List<Integer> successRemove =  new ArrayList<>();
                  for (int i=0;i<isDeleted.length;i++){
                      boolean flag = isDeleted[i];

                      if (!flag){
                          isAllDeleted= false;
                          failRemove.add(uids[i]);
                      }else{
                          successRemove.add(uids[i]);
                      }
                  }
                  if (!isAllDeleted){
                      state = RetState.ERROR;
                  }
                data.put("failure",failRemove);
                data.put("success",successRemove);

            }else{
                state = RetState.UNAUTHORIZED;
            }


        }catch (Exception e){
            e.printStackTrace();
            state = RetState.BADPARAMS;
        }

        return RetJson.retJson(state,data);
    }
>>>>>>> upstream/main
}
