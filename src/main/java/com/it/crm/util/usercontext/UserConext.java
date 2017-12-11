package com.it.crm.util.usercontext;

import com.it.crm.domain.Employee;
import com.it.crm.domain.Permission;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-07 21:25
 */
public abstract class UserConext {

    public static final String USER_IN_SESSION = "loginInfo";

    public static final String PERMISSION_IN_SESSION = "permissionInfo";

    /**
     *  获取Session
     * @return  HttpSession
     */
    public static HttpSession getSession(){


        return getRequest().getSession();
    }


    /**
     *  获取当前请求
     * @return  HttpSession
     */
    public static HttpServletRequest getRequest(){


        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }


    /**
     *  把登录的Employee对象放入Session中
     *
     * @param e  employee对象
     */
    public static  void putUserInSession(Employee e){


        getSession().setAttribute(PERMISSION_IN_SESSION,e);

    }

    /**
     *
     *  从Session取出当前登录的Employee
     *
     * @return  Employee实例,当Session中的对象不是Employee实例,返回一个空
     */
    public static  Employee getCurrentUser(){

        Object o = getSession().getAttribute(PERMISSION_IN_SESSION);

        if(o != null && o.getClass() == Employee.class){

            return (Employee) o;
        }

        return null;
    }

    /**
     *  把权限放入Session中
     * @param list
     */
    public static void  putPermissionInSession(List<Permission> list){

        getSession().setAttribute(USER_IN_SESSION,list);

    }

    /**
     *  获取用户的权限
     * @return list
     */
    public static List<Permission> getUserPermission(){


        return (List)getSession().getAttribute(USER_IN_SESSION);
    }

}
