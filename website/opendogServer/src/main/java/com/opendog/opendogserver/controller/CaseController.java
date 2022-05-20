package com.opendog.opendogserver.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opendog.opendogserver.entity.Case;
import com.opendog.opendogserver.entity.Project;
import com.opendog.opendogserver.service.CaseService;
import com.opendog.opendogserver.service.TokenService;
import com.opendog.opendogserver.utils.IOUtils;
import com.opendog.opendogserver.utils.MHttpHeader;


import com.opendog.opendogserver.utils.RetJson;
import com.opendog.opendogserver.utils.RetState;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.HashMap;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/case/")
public class CaseController {

    @Value( "${file.upload.url}")
    private String filesDirPath;

    @Autowired
    CaseService caseService;

    @Autowired
    TokenService tokenService;
    /*
     * @Author opendog
     * @Description 
     * 暂时不实现
     * @Date 2022/4/7 19:49
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "add_case")
    public RetJson addCase(@RequestParam("files") MultipartFile[] files, HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String,String> data =new HashMap<>();

        try{
            //获取参数
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();
            //验证token合法性
            if (tokenService.checkTokenIsValid(uid,token))
            {
                Case icase = new Case();
                String caseName = request.getParameter("caseName");
                icase.setCaseName(caseName);
                //这三个可不填
                String comment = request.getParameter("comment");
                String taskId = request.getParameter("taskId");
                String projectId = request.getParameter("projectId");
                icase.setUserId(uid);
                icase.setComment(comment);
                icase.setTaskid(Integer.parseInt(taskId));
                icase.setProjectid(Integer.parseInt(projectId));

                String dataPath = "";
                for (MultipartFile file: files){
                    String fileName =file.getOriginalFilename();
                    //存在用户目录下
                    File fileStorage = new File(filesDirPath + File.separator+ String.format("%d", uid)+File.separator + fileName);
                    //检查 root/uid/ 目录是否存在
                    if (!fileStorage.getParentFile().exists()){
                        fileStorage.getParentFile().mkdirs();
                    }
                    //文件存入路径
                    file.transferTo(fileStorage);
                    dataPath = fileName;
                }
                icase.setDataId(dataPath);
                //默认写0
                icase.setAccessState(0);
                if (caseService.insertCase(icase)){
                    msg = "上传成功";
                    state = RetState.SUCCESS;
                }else {
                    msg = "已创建同名";
                    state = RetState.ERROR;
                }

            }
            else{
                msg = "授权不合法";
                state = RetState.ERROR;
            }


        }catch (Exception e){
            e.printStackTrace();
            msg = "异常报错";
            state = RetState.ERROR;
        }

        return RetJson.retJson(state,msg,data);
    }

    /*
     * @Author opendog
     * @Description 
     * @Date 2022/4/7 19:49
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "delete_case")
    public RetJson deleteCase(HttpServletRequest request){


        RetState state = RetState.SUCCESS;
        String msg = "完全删除";
        Map<String, List<Integer>> data =new HashMap<>();
        List<Integer> isUndeleted = new ArrayList<>();
        data.put("failure",isUndeleted);
        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();
            //将json转为JSONObject
            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            Integer[] caseIds =  params.getJSONArray("caseId").toArray(new Integer[ params.getJSONArray("caseId").size()]);
            List<Case> cases=new ArrayList<>();
            if (tokenService.checkTokenIsValid(uid,token)){
                for (Integer caseId : caseIds) {
                    if (!caseService.deleteCase(caseId)) {
                        state = RetState.ERROR;
                        msg = "未完全删除成功";
                        isUndeleted.add(caseId);
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
     * @Date 2022/4/7 19:51
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "update_case")
    public RetJson updateCase(HttpServletRequest request){

        RetState state = RetState.SUCCESS;
        String msg = "更新成功";
        Map<String,Case> data =new HashMap<>();

        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            //将RequestBody转为JSONObject
            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            int cid = params.getInteger("caseId");
            int tid = params.getInteger("taskId");
            String caseName = params.getString("caseName");
            String comment = params.getString("comment");
            if (tokenService.checkTokenIsValid(uid,token))
            {
                //检查CID是否合法
                Case icase =caseService.selectCase(cid);
                if (icase!=null){
                    if (tid > 0)
                        icase.setTaskid(tid);
                    if (cid >0)
                        icase.setCaseId(cid);
                    if (!caseName.equals(""))
                        icase.setCaseName(caseName);
                    icase.setComment(comment);
                    Case cases=caseService.updateCase(icase);
                    if (cases != null){
                        state = RetState.SUCCESS;
                        msg = "记录更新成功";
                        data.put("case",cases);
                    }else{
                        state = RetState.ERROR;
                        msg = "记录更新失败";
                    }
                }else{
                    state = RetState.ERROR;
                    msg = "cid 不合法";
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
     * @Date 2022/4/7 19:51
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "select_case")
    public RetJson selectCase(HttpServletRequest request){

        RetState state = RetState.SUCCESS;
        String msg = "更新成功";
        Map<String,List<Case>> data =new HashMap<>();
        try{
            //获取请求头
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();
            if (tokenService.checkTokenIsValid(uid,token)){
                List<Case> caseList=caseService.selectCaseByUid(uid);
                if (caseList.size()!=0){
                    state = RetState.SUCCESS;
                    msg = "case存在";
                    data.put("success",caseList);
                }else{
                    state = RetState.ERROR;
                    msg = "case不存在";
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
     * @Date 2022/4/7 19:51
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "get_case_detail")
    public RetJson getCaseDetail(HttpServletRequest request){

        RetState state=RetState.SUCCESS;
        String msg="更新成功!";
        Map<String,List<Object>> data =new HashMap<>();

        try{
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            Integer[] caseIds =  params.getJSONArray("caseId").toArray(new Integer[ params.getJSONArray("caseId").size()]);
            List<Object> isUnselected = new ArrayList<>();
            List<Object> isSelected = new ArrayList<>();
            if(tokenService.checkTokenIsValid(uid,token)){
                for(int cid :caseIds){
                    Case icase=caseService.selectCase(cid);
                    if(icase==null){
                        isUnselected.add(cid);
                    }else{
                        isSelected.add(icase);
                    }
                }
                if(isUnselected.size()==0){
                    state = RetState.SUCCESS;
                    msg = "全部查询成功";
                    data.put("success",isSelected);
                }else{
                    state = RetState.ERROR;
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

    /*
     * @Author opendog
     * @Description
     * @Date 2022/4/7 19:51
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "get_case_detail_with_passwd")
    public RetJson getCaseDetailWithPasswd(HttpServletRequest request){

        RetState state=RetState.SUCCESS;
        String msg="更新成功!";
        Map<String,Object> data =new HashMap<>();

        try{
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            String password=params.getString("passwd");
            int caseId = params.getInteger("caseId");

            if(tokenService.checkTokenIsValid(uid,token)){

                Case icase=caseService.getCaseWithPasswd(caseId,password);
                if(icase==null){
                    state = RetState.ERROR;
                    msg = "查询失败";

                }else{
                    state= RetState.SUCCESS;
                    msg = "查询成功";
                    data.put("case",icase);
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
     * @Date 2022/4/7 19:51
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "pigeonhole_case")
    public RetJson pigeonholeCase(HttpServletRequest request){
        RetState state=RetState.SUCCESS;
        String msg="更新成功!";
        Map<String,Object> data =new HashMap<>();

        try{
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();
            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            int caseId = params.getInteger("caseId");
            int projectId = params.getInteger("projectId");

            if (tokenService.checkTokenIsValid(uid,token)){
                //检查case是否存在
                Case icase = caseService.selectCase(caseId);
                if (icase!=null){
                    int prevPid = icase.getProjectid();
                    if (prevPid!=0){
                        state = RetState.ERROR;
                        msg = String.format("以存在项目,请想从项目 %d 移除 用例 %d", prevPid,caseId);
                    }else{
                        icase.setProjectid(projectId);
                        caseService.updateCase(icase);

                        state = RetState.SUCCESS;
                        msg = "成功";
                        data.put("case",icase);
                    }


                }else  {
                    state = RetState.ERROR;
                    msg ="用例不存咋";
                }

            }else{
                state  = RetState.ERROR;
                msg = "授权不合法";
            }


        }catch (Exception e){
            e.printStackTrace();
            state = RetState.ERROR;
        }

        return RetJson.retJson(state,msg,data);
    }


    @PostMapping(value = "remove_cases_from_project")
    public RetJson removeCasesFromProject(HttpServletRequest request){
        RetState state=RetState.SUCCESS;
        String msg="更新成功!";
        Map<String,List<Integer>> data =new HashMap<>();

        try{
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();
            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            Integer[] caseIds =  params.getJSONArray("caseId").toArray(new Integer[ params.getJSONArray("caseId").size()]);
            int projectId = params.getInteger("projectId");
            List<Integer> success = new ArrayList<>();
            List<Integer> failure = new ArrayList<>();
            boolean isAllSuccess = true;
            if (tokenService.checkTokenIsValid(uid,token)){
                for (Integer caseId: caseIds){
                    Case icase = caseService.selectCase(caseId);
                    if (icase!=null){
                        if (icase.getProjectid() == projectId)
                        {
                            icase.setProjectid(0);
                            success.add(caseId);
                        }else{
                            failure.add(caseId);
                            isAllSuccess = false;
                        }

                    }else{
                        failure.add(caseId);
                        isAllSuccess = false;
                    }

                }
                if (isAllSuccess){
                    state = RetState.SUCCESS;
                    msg = "全部查询成功";
                    data.put("success",success);
                    data.put("failure",failure);
                }else {
                    state = RetState.ERROR;
                    msg = "未能全部查询成功";
                    data.put("success",success);
                    data.put("failure",failure);
                }

            }else {
                state  = RetState.ERROR;
                msg = "授权不合法";
            }



        }catch (Exception e){
            e.printStackTrace();
            state = RetState.ERROR;
        }
        return RetJson.retJson(state,msg,data);
    }

    @PostMapping(value = "get_case_data")
    private RetJson getCaseData(HttpServletRequest request){
        RetState state=RetState.SUCCESS;
        String msg="";
        Map<String,Object> data =new HashMap<>();

        try{
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();
            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            int caseId = params.getInteger("caseId");
            if (tokenService.checkTokenIsValid(uid,token)){
                Case icase = caseService.selectCase(caseId);
                if (icase!=null){
                    String fileName = icase.getCaseName();
                    int caseOwner = icase.getUserId();
                    String caseFilePath = filesDirPath + File.separator+ String.format("%d", uid)+File.separator + icase.getDataId();
                    System.out.println(caseFilePath);

                    File file = new File(caseFilePath);
                    if (file.exists()){
                        String content = FileUtils.readFileToString(file, "UTF-8");
                        System.out.println(content);
                        JSONObject object = JSON.parseObject(content);
                        data.put("case_data",object);
                        state = RetState.SUCCESS;
                        msg = "获取成功";

                    }else{
                        state = RetState.ERROR;
                        msg = "文件不存在";
                    }

                }else{
                    state = RetState.ERROR;
                    msg = "case 不存在";
                }

            }else{
                state  = RetState.ERROR;
                msg = "授权不合法";
            }

        }catch (Exception e){

            e.printStackTrace();
            state = RetState.ERROR;
        }

        return RetJson.retJson(state,msg,data);
    }


    //TODO 未来实现用例分享
//    @Value("${ip}")
//    private String ip;
//    /*
//     * @Author opendog
//     * @Description
//     * 暂不实现
//     * @Date 2022/4/7 19:51
//     * @Param [request]
//     * @return com.opendog.opendogserver.utils.RetJson
//     **/
//    @PostMapping(value = "share_case")
//    public RetJson shareCase(HttpServletRequest request){
//        RetState state=RetState.SUCCESS;
//        String msg="更新成功!";
//        Map<String,Object> data =new HashMap<>();
//
//        try{
//            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
//            int uid = mHttpHeader.getUid();
//            String token = mHttpHeader.getToken();
//            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
//            int caseId = params.getInteger("caseId");
//            String accessPassword = params.getString("accessPassword");
//            int accessState = params.getInteger("accessState");
//
//            if (tokenService.checkTokenIsValid(uid,token)){
//                Case icase = caseService.selectCase(uid,caseId);
//                if (icase!=null){
//                    icase.setAccessState(1);
//                    icase.setAccessPasswd(accessPassword);
//                    String url = String.format("http://%s/access_case/%d_%d",ip,System.currentTimeMillis(),caseId );
//                    icase.setAccessUrl(url);
//
//                    data.put("url",url);
//                }
//
//            }else{
//                state=RetState.ERROR;
//                msg="授权不合法!";
//
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//            state = RetState.ERROR;
//        }
//
//        return RetJson.retJson(state,msg,data);
//    }
//
//    @PostMapping(value = "access_case")
//    public RetJson accessCase(HttpServletRequest request){
//        RetState state=RetState.SUCCESS;
//        String msg="更新成功!";
//        Map<String,Object> data =new HashMap<>();
//
//        try{
//            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
//            int uid = mHttpHeader.getUid();
//            String token = mHttpHeader.getToken();
//            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
//            int caseId = params.getInteger("caseId");
//            String accessPassword = params.getString("accessPassword");
//            int accessState = params.getInteger("accessState");
//
//            if (tokenService.checkTokenIsValid(uid,token)){
//                Case icase = caseService.selectCase(uid,caseId);
//                if (icase!=null){
//                    icase.setAccessState(1);
//                    icase.setAccessPasswd(accessPassword);
//                    String url = String.format("http://%s/access_case/%d_%d",ip,System.currentTimeMillis(),caseId );
//                    icase.setAccessUrl(url);
//
//                    data.put("url",url);
//                }
//
//            }else{
//                state=RetState.ERROR;
//                msg="授权不合法!";
//
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//            state = RetState.ERROR;
//        }
//
//        return RetJson.retJson(state,msg,data);
//    }
}
