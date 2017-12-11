package com.it.crm.controller;

import com.it.crm.domain.Employee;
import com.it.crm.page.EmployeeQueryObject;
import com.it.crm.service.IEmployeeService;
import com.it.crm.util.permission.CheckPermission;
import com.it.crm.vo.PageResult;
import com.it.crm.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-08 00:37
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeServiceImpl;

    @RequestMapping("/list")
    public String index(){

        return "/employee/index";
    }

    @RequestMapping("/show")
    @ResponseBody
    public PageResult list(EmployeeQueryObject e){

        return employeeServiceImpl.queryPage(e);
    }


    @CheckPermission("员工增加")
    @RequestMapping("/add")
    @ResponseBody
    public ResultMessage addOrUpdateEmployee(Employee e){


         ResultMessage resultMessage = new ResultMessage();

        try{

            employeeServiceImpl.addOrUpdateEmployee(e);

            resultMessage.setSuccess(true);
            resultMessage.setMessage("添加成功");

        }catch(Exception ex){

            ex.printStackTrace();

            resultMessage.setMessage(ex.getMessage());

        }

        return resultMessage;
    }


    @CheckPermission("员工删除")
    @RequestMapping("/remove")
    @ResponseBody
    public ResultMessage remove(Long id){

        ResultMessage msg = new ResultMessage();

        try{

            employeeServiceImpl.deleteEmployee(id);

            msg.setSuccess(true);
            msg.setMessage("删除成功");

        }catch(Exception ex){

            ex.printStackTrace();

            msg.setMessage(ex.getMessage());

        }

        return msg;

    }

}
