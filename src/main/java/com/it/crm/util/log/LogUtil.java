package com.it.crm.util.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.it.crm.service.ILogService;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-09 22:57
 */
public class LogUtil {

    @Autowired
    private ILogService loginServiceImpl;

    public void log(JoinPoint joinPoint) throws JsonProcessingException {

/*
        //如果是ILogService则返回,避免死循环
        if(joinPoint.getTarget() instanceof ILogService) return;



        Log log = new Log();

        log.setFunction(joinPoint.getSignature().getDeclaringTypeName()+":"+joinPoint.getSignature().getName());

        log.setOperationTime(new Date());

        //方法的参数
         String param = JSONUtil.getJson(joinPoint.getArgs());

        System.out.println(param);

        log.setParams(param);

        //获取客户端ip
        log.setIp(UserConext.getRequest().getRemoteAddr());



        //设置操作的用户
        log.setOperationUser(UserConext.getCurrentUser());

        loginServiceImpl.insert(log);
*/
    }


}
