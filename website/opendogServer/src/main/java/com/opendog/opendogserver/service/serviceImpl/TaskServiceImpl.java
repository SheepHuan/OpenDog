package com.opendog.opendogserver.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.opendog.opendogserver.entity.SharedTask;
import com.opendog.opendogserver.entity.Task;
import com.opendog.opendogserver.entity.User;
import com.opendog.opendogserver.mapper.SharedTaskMapper;
import com.opendog.opendogserver.mapper.TaskMapper;
import com.opendog.opendogserver.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.TaskManagementConfigUtils;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
=======
import java.util.*;
>>>>>>> upstream/main

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    SharedTaskMapper sharedTaskMapper;

    @Override
    public Task insertTask(Task task) {
        task.setCreatedTime(new Date(System.currentTimeMillis()));
        task.setUpdatedTime(new Date(System.currentTimeMillis()));
        //检查uid下有无同名task
        Map<String,Object> map = new HashMap<>();
        map.put("uid",task.getUid());
        map.put("task_name",task.getTaskName());
        List<Task> selectedTasks = selectTaskByMap(map);
        if (selectedTasks!=null)
            return null;

        taskMapper.insert(task);
        return task;
    }

    @Override
    public Task updateTask(Task task) {
<<<<<<< HEAD
        task.setUpdatedTime(new Date(System.currentTimeMillis()));
        taskMapper.updateById(task);
        return task;
=======

        Map <String,Object> map = new HashMap<>();
        map.put("uid",task.getUid());
        map.put("task_name",task.getTaskName());

        List<Task> sameNameTasks = taskMapper.selectByMap(map);
        boolean haveSameNameTask = false;
        for (Task sameNameTask : sameNameTasks){
            if (sameNameTask.getTid() != task.getTid()) {
                haveSameNameTask = true;
                break;
            }
        }
         if (!haveSameNameTask){
             task.setUpdatedTime(new Date(System.currentTimeMillis()));
             taskMapper.updateById(task);
             return task;
         }else {
             return null;
         }

>>>>>>> upstream/main
    }

    @Override
    public Task updateTaskComment(int tid, String comment) {
        Task task = selectTaskByTid(tid);
        if (task != null){
            task.setComment(comment);
            return updateTask(task);
        }
        return null;
    }

    @Override
    public Task updateTaskName(int tid, String taskName) {
        Task task = selectTaskByTid(tid);
        if (task != null){
            task.setTaskName(taskName);
            return updateTask(task);
        }
        return null;
    }

    @Override
    public Task updateTaskName(int tid, String taskName, String comment) {
        Task task = selectTaskByTid(tid);
        if (task != null){
            task.setTaskName(taskName);
            task.setComment(comment);
            return updateTask(task);
        }
        return null;
    }

    @Override
    public boolean deleteTask(int tid) {
        Task task = selectTaskByTid(tid);
        if (task!=null){
            taskMapper.deleteById(tid);
            return true;
        }else
            return false;
    }

    @Override
    public Task selectTaskByTid(int tid) {
        return taskMapper.selectById(tid);
    }

    @Override
    public List<Task> selectTaskByUid(int uid) {
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        return selectTaskByMap(map);
    }

    @Override
    public List<Task> selectTaskByMap(Map<String, Object> map) {
        List<Task> tasks = taskMapper.selectByMap(map);
        if (tasks.size()>0)
            return tasks;
        else
            return null;
    }

    @Override
    public List<Integer> selectUsersFromTask(int tid) {
        List<Integer> userIds = sharedTaskMapper.selectUidByTid(tid);
        if (userIds.size()>0)
            return userIds;
        return null;
    }

    @Override
    public boolean[] removeUsersFromTask(int tid, int[] uids) {
        boolean[] isRemoved = new boolean[uids.length];
        for (int i=0 ; i <uids.length;i++){
            SharedTask sharedTask = selectSharedTaskByUidAndTid(uids[i],tid);
            if (sharedTask!=null)
            {
                sharedTaskMapper.deleteById(sharedTask);
                isRemoved[i] = true;
            }else{
                isRemoved[i] = false;
            }
        }
        return isRemoved;
    }

    @Override
<<<<<<< HEAD
=======
    public boolean[] removeUsersFromTask(int tid, Integer[] uids) {
        boolean[] isRemoved = new boolean[uids.length];
        for (int i=0 ; i <uids.length;i++){
            SharedTask sharedTask = selectSharedTaskByUidAndTid(uids[i],tid);
            if (sharedTask!=null)
            {
                sharedTaskMapper.deleteById(sharedTask);
                isRemoved[i] = true;
            }else{
                isRemoved[i] = false;
            }
        }
        return isRemoved;
    }

    @Override
>>>>>>> upstream/main
    public boolean[] addUsersToTask(int tid, int[] uids) {
        boolean[] isRemoved = new boolean[uids.length];
        for (int i=0 ; i <uids.length;i++){
            SharedTask sharedTask = selectSharedTaskByUidAndTid(uids[i],tid);
            if (sharedTask==null)
            {
                sharedTask = new SharedTask();
                sharedTask.setTid(tid);
                sharedTask.setUid(uids[i]);
                sharedTaskMapper.insert(sharedTask);
                isRemoved[i] = true;
            }else{
                isRemoved[i] = false;
            }
        }
        return isRemoved;
    }

    @Override
<<<<<<< HEAD
=======
    public boolean[] addUsersToTask(int tid, Integer[] uids) {
        boolean[] isRemoved = new boolean[uids.length];
        for (int i=0 ; i <uids.length;i++){
            SharedTask sharedTask = selectSharedTaskByUidAndTid(uids[i],tid);
            if (sharedTask==null)
            {
                sharedTask = new SharedTask();
                sharedTask.setTid(tid);
                sharedTask.setUid(uids[i]);
                sharedTaskMapper.insert(sharedTask);
                isRemoved[i] = true;
            }else{
                isRemoved[i] = false;
            }
        }
        return isRemoved;
    }

    @Override
>>>>>>> upstream/main
    public SharedTask selectSharedTaskByUidAndTid(int uid, int tid) {
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("tid",tid);
        List<SharedTask> sharedTasks = sharedTaskMapper.selectByMap(map);
        if (sharedTasks.size()>0)
            return sharedTasks.get(0);
        return null;
    }

<<<<<<< HEAD
=======
    @Override
    public List<Integer> selectUserFromSharedTask(int tid) {
        Map<String,Object> map = new HashMap<>();
        map.put("tid",tid);
        List<SharedTask> sharedTasks = sharedTaskMapper.selectByMap(map);
        List<Integer> uids =  new ArrayList<>();
        if (sharedTasks.size()>0)
        {
            for (SharedTask task : sharedTasks){
                uids.add(task.getUid());
            }
            return uids;
        }
        return null;
    }

>>>>>>> upstream/main

}
