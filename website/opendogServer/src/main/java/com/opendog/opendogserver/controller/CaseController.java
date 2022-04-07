package com.opendog.opendogserver.controller;

import com.opendog.opendogserver.utils.RetJson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/case/")
public class CaseController {


    @PostMapping(value = "add_case")
    public RetJson addCase(HttpServletRequest request){

        return null;
    }

    @PostMapping(value = "delete_case")
    public RetJson deleteCase(HttpServletRequest request){

        return null;
    }

    @PostMapping(value = "update_case")
    public RetJson updateCase(HttpServletRequest request){

        return null;
    }

    @PostMapping(value = "select_case")
    public RetJson selectCase(HttpServletRequest request){

        return null;
    }

    @PostMapping(value = "get_case_detail")
    public RetJson getCaseDetail(HttpServletRequest request){

        return null;
    }

    @PostMapping(value = "get_case_detail_with_passwd")
    public RetJson getCaseDetailWithPasswd(HttpServletRequest request){

        return null;
    }

    @PostMapping(value = "pigeonhole_case")
    public RetJson pigeonholeCase(HttpServletRequest request){

        return null;
    }

    @PostMapping(value = "share_case")
    public RetJson shareCase(HttpServletRequest request){

        return null;
    }

}
