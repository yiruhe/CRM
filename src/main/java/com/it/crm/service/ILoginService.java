package com.it.crm.service;

import com.it.crm.domain.Employee;

/**
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-07 21:15
 */
public interface ILoginService {
    void login(String username, String password);

    Employee getEmployee(Long id);
}
