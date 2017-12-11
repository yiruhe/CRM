package com.it.crm.controller;

import com.it.crm.service.ILoginService;
import com.it.crm.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-07 21:41
 */

@Controller
public class LoginController {


    @Autowired
    private ILoginService loginServiceImpl;

    @RequestMapping("/login/index")
    public String loginIndex(){


        return "login";
    }

    @RequestMapping("/login/check")
    @ResponseBody
    public ResultMessage login(String username,String password){

        ResultMessage resultMessage = new ResultMessage();

       try{

            loginServiceImpl.login(username, password);

            resultMessage.setMessage("登录成功");
            resultMessage.setSuccess(true);
        }catch (Exception e){

            e.printStackTrace();

            resultMessage.setMessage(e.getMessage());
        }


        return resultMessage;

    }


    @RequestMapping("/index")
    public String index(){


        return "index";
    }

}
