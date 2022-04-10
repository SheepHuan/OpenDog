package com.opendog.opendogserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.opendog.opendogserver.entity.Case;
import com.opendog.opendogserver.entity.Project;
import com.opendog.opendogserver.service.CaseService;
import com.opendog.opendogserver.service.TokenService;
import com.opendog.opendogserver.utils.IOUtils;
import com.opendog.opendogserver.utils.MHttpHeader;
import com.opendog.opendogserver.utils.RetJson;
import com.opendog.opendogserver.utils.RetState;
import lombok.Data;
import netscape.javascript.JSObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/case/")
public class CaseController {

    //@Value("${file.upload.url}")
    private String filesDirPath;
    BasicJsonParser jsonParser = new BasicJsonParser();


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
    public RetJson addCase(@RequestParam("files")MultipartFile[] files, HttpServletRequest request){
        RetState state = RetState.SUCCESS;
        String msg = "";
        Map<String,String> data =new HashMap<>();

        try{
            //获取参数
            int uid = Integer.parseInt(request.getParameter("uid"));
            String token = request.getParameter("token");
            //验证token合法性
            if (tokenService.checkTokenIsValid(uid,token))
            {
                Case icase = new Case();
                String caseName = request.getParameter("caseName");
                icase.setCaseName(caseName);
                //这三个可不填
                String comment = request.getParameter("comment");
                if (!comment.equals("None"))
                    icase.setComment(comment);
                String taskId = request.getParameter("taskId");
                if (!taskId.equals("None"))
                    icase.setTaskid(Integer.parseInt(taskId));
                String projectId = request.getParameter("projectId");
                if (!projectId.equals("None"))
                    icase.setProjectid(Integer.parseInt(projectId));

                String dataPath = "";
                for (MultipartFile file: files){
                    String fileName =file.getOriginalFilename();
                    File fileStorage = new File(filesDirPath + File.separator + fileName);
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
                    msg = "数据库插入失败";
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
                    if (!caseService.deleteCase(cases)) {
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
            int cid = params.getInteger("cid");
            int tid = params.getInteger("tid");
            String caseName = params.getString("caseName");
            String comment = params.getString("comment");
            if (tokenService.checkTokenIsValid(uid,token))
            {
                //检查CID是否合法
                List<Case> caseList =caseService.selectCase(uid);
                if (caseList.size()!=0){
                    if (tid > 0)
                        caseList.get(uid).setTaskid(tid);
                    if (cid >0)
                        caseList.get(uid).setCaseId(cid);
                    if (!caseName.equals(""))
                        caseList.get(uid).setCaseName(caseName);
                    if (!comment.equals(""))
                        caseList.get(uid).setComment(comment);
                    Case cases=caseService.updateCase(caseList.get(uid));
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
            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            if (tokenService.checkTokenIsValid(uid,token)){
                List<Case> caseList=new ArrayList<>();
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
        //data.put("success",new ArrayList<>());
        //data.put("failure",new ArrayList<>());
        try{
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            Integer[] caseIds =  params.getJSONArray("uid").toArray(new Integer[ params.getJSONArray("uid").size()]);
            List<Object> isUnselected = new ArrayList<>();
            List<Object> isSelected = new ArrayList<>();
            if(tokenService.checkTokenIsValid(uid,token)){
                for(int cid :caseIds){
                    List<Case> caseList=caseService.selectCase(cid);
                    if(caseList.size()==0){
                        isUnselected.add(cid);
                    }else{
                        isSelected.add(caseList);
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
        Map<String,List<Object>> data =new HashMap<>();
        data.put("success",new ArrayList<>());
        data.put("failure",new ArrayList<>());
        try{
            MHttpHeader mHttpHeader = MHttpHeader.getHeadFromRequest(request);
            int uid = mHttpHeader.getUid();
            String token = mHttpHeader.getToken();

            JSONObject params = IOUtils.stringConvert2Json(IOUtils.inputStream2String(request.getInputStream()));
            String password=params.getString("password");
            Integer[] caseIds =  params.getJSONArray("uid").toArray(new Integer[ params.getJSONArray("uid").size()]);
            List<Object> isUnselected = new ArrayList<>();
            List<Object> isSelected = new ArrayList<>();
            if(tokenService.checkTokenIsValid(uid,token)){
                for(int cid :caseIds){
                    Case cases=caseService.getCaseWithPasswd(cid,password);
                    if(cases==null){
                        isUnselected.add(cid);
                    }else{
                        isSelected.add(cases);
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
    @PostMapping(value = "pigeonhole_case")
    public RetJson pigeonholeCase(HttpServletRequest request){

        return null;
    }

    /*
     * @Author opendog
     * @Description
     * 暂不实现
     * @Date 2022/4/7 19:51
     * @Param [request]
     * @return com.opendog.opendogserver.utils.RetJson
     **/
    @PostMapping(value = "share_case")
    public RetJson shareCase(HttpServletRequest request){

        return null;
    }

}
