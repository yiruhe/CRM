package com.it.crm.controller;

import com.it.crm.page.PermissionQueryObject;
import com.it.crm.service.IPermissionService;
import com.it.crm.vo.PageResult;
import com.it.crm.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-10 18:10
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionServiceImpl;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;


    @RequestMapping("/query/name")
    @ResponseBody
    public PageResult queryAllName(PermissionQueryObject p){


       return permissionServiceImpl.getAllName(p);
    }


    @RequestMapping("/init/all")
    @ResponseBody
    public ResultMessage loadPerPermission(){


        //利用requestMappingHandlerMapping实例获取所有controller的所有方法
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();


        Set<Map.Entry<RequestMappingInfo, HandlerMethod>> entry = map.entrySet();

        Set<Method> methods = new HashSet<>(entry.size());

        //把所有的方法放入set中
        for(Map.Entry<RequestMappingInfo, HandlerMethod> e:entry){

            methods.add(e.getValue().getMethod());

        }

        //保存
        permissionServiceImpl.loadPermission(methods);


        return new ResultMessage(true,"加载成功");
    }


    @RequestMapping("/subject")
    public String subjectAccess(){


        return "/permission/index";
    }

}
