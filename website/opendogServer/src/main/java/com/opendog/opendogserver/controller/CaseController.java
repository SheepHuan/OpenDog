package com.opendog.opendogserver.controller;

import com.opendog.opendogserver.utils.RetJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/case/")
public class CaseController {

    @Value("${file.upload.url}")
    private String filesDirPath;
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

        return null;
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
