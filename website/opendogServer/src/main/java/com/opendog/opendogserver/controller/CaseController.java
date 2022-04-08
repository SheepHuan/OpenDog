package com.opendog.opendogserver.controller;

import com.opendog.opendogserver.entity.Case;
import com.opendog.opendogserver.service.CaseService;
import com.opendog.opendogserver.service.TokenService;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/case/")
public class CaseController {

    @Value("${file.upload.url}")
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
                String sCase = request.getParameter("case");
                //解析String -> Map <String, Object>
                Map<String,Object> icase=jsonParser.parseMap(sCase);
                String caseName = (String) icase.get("caseName");

                //这三个可不填
                String comment = (String) icase.get("comment");
                int taskId = Integer.parseInt((String) icase.get("taskId"));
                int projectId = Integer.parseInt((String) icase.get("projectId"));
                String dataPath = "";
                for (MultipartFile file: files){
                    String fileName =file.getOriginalFilename();
                    File fileStorage = new File(filesDirPath + File.separator + fileName);
                    //文件存入路径
                    file.transferTo(fileStorage);
                    dataPath = fileName;
                }
                Case caseitem = new Case();
                caseitem.setCaseName(caseName);
                caseitem.setComment(comment);
                caseitem.setProjectid(projectId);
                caseitem.setTaskid(taskId);
                caseitem.setDataId(dataPath);
                //默认写0
                caseitem.setAccessState(0);
                caseService.insertCase(caseitem);
                msg = "上传成功";
                state = RetState.SUCCESS;
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

        return null;
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

        return null;
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

        return null;
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

        return null;
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

        return null;
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
