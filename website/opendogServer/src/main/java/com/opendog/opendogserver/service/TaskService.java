package com.opendog.opendogserver.service;

import com.opendog.opendogserver.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TaskService {


    Task insertTask(Task task);

    Task updateTask(Task task);

    Task updateTaskComment(int tid,String comment);

    Task updateTaskName(int tid, String taskName);

    Task updateTaskName(int tid, String taskName,String comment);

    boolean deleteTask(int tid);

    Task selectTaskByTid(int tid);

    List<Task> selectTaskByUid(int uid);

    List<Task> selectTaskByMap(Map<String,Object> map);


}
