package com.it.crm.service;

import com.it.crm.domain.Log;

/**
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-09 22:52
 */
public interface ILogService {
    /**
     * 添加log日志
     * @param log log对象
     */
    void insert(Log log);
}
