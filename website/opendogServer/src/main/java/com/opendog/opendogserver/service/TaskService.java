package com.opendog.opendogserver.service;

import com.opendog.opendogserver.entity.SharedTask;
import com.opendog.opendogserver.entity.Task;
import com.opendog.opendogserver.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TaskService {

    /*
     * @Author opendog
     * @Description
     * 向表插入一个Task记录
     * @Date 2022/4/8 21:51
     * @Param [task]
     * @return com.opendog.opendogserver.entity.Task
     **/
    Task insertTask(Task task);

    /*
     * @Author opendog
     * @Description
     * 更新Task记录
     * 确保uid 下无 命名冲突
     * @Date 2022/4/8 21:51
     * @Param [task]
     * @return com.opendog.opendogserver.entity.Task
     **/
    Task updateTask(Task task);

    /*
     * @Author opendog
     * @Description
     * 更新Task#comment
     * @Date 2022/4/8 21:51
     * @Param [tid, comment]
     * @return com.opendog.opendogserver.entity.Task
     **/
    Task updateTaskComment(int tid,String comment);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/8 21:51
     * @Param [tid, taskName]
     * @return com.opendog.opendogserver.entity.Task
     **/
    Task updateTaskName(int tid, String taskName);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/8 21:51
     * @Param [tid, taskName, comment]
     * @return com.opendog.opendogserver.entity.Task
     **/
    Task updateTaskName(int tid, String taskName,String comment);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/8 21:51
     * @Param [tid]
     * @return boolean
     **/
    boolean deleteTask(int tid);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/8 21:51
     * @Param [tid]
     * @return com.opendog.opendogserver.entity.Task
     **/
    Task selectTaskByTid(int tid);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/8 21:51
     * @Param [uid]
     * @return java.util.List<com.opendog.opendogserver.entity.Task>
     **/
    List<Task> selectTaskByUid(int uid);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/8 21:51
     * @Param [map]
     * @return java.util.List<com.opendog.opendogserver.entity.Task>
     **/
    List<Task> selectTaskByMap(Map<String,Object> map);

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/8 21:51
     * @Param [tid]
     * @return java.util.List<com.opendog.opendogserver.entity.User>
     **/
    List<Integer> selectUsersFromTask(int tid);

    /*
     * @Author opendog
     * @Description
     * 将某些用户从Task中移除
     * @Date 2022/4/8 21:51
     * @Param [tid, uids]
     * @return boolean[]
     **/
    boolean[] removeUsersFromTask(int tid, int[] uids);

    /*
     * @Author opendog
     * @Description
     * 向Task中添加一些用户
     * @Date 2022/4/8 21:51
     * @Param [tid, uids]
     * @return boolean[]
     **/
    boolean[] addUsersToTask(int tid, int[] uids);

    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/9 11:25
     * @Param [tid, uids]
     * @return boolean[]
     **/
    boolean[] addUsersToTask(int tid, Integer[] uids);

    /*
     * @Author opendog
     * @Description
     * 根据uid 和 tid 选取task
     * @Date 2022/4/8 22:23
     * @Param [uid, tid]
     * @return com.opendog.opendogserver.entity.SharedTask
     **/
    SharedTask selectSharedTaskByUidAndTid(int uid,int tid);


}
