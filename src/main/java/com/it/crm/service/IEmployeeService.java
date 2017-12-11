package com.it.crm.service;

import com.it.crm.domain.Employee;
import com.it.crm.page.EmployeeQueryObject;
import com.it.crm.vo.PageResult;

import java.util.List;

/**
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-08 00:58
 */
public interface IEmployeeService {

    List<Employee> queryAll();

    /**
     * 添加或者更新员工
     * @param employee
     */
    void addOrUpdateEmployee(Employee employee);

    /**
     * 删除员工
     * @param id 用户id
     */
    void deleteEmployee(Long id);

    /**
     * 分页查询
     * @param e  查询对象
     * @return  分页结果
     */
    PageResult queryPage(EmployeeQueryObject e);
}
