package com.it.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.crm.domain.Employee;
import com.it.crm.mapper.EmployeeMapper;
import com.it.crm.page.EmployeeQueryObject;
import com.it.crm.service.IEmployeeService;
import com.it.crm.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-08 00:58
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public List<Employee> queryAll(){

        return  employeeMapper.selectAll(new EmployeeQueryObject());
    }

    @Override
    public void addOrUpdateEmployee(Employee employee){

        //判断
        if(employee == null)throw new RuntimeException(this.getClass()+"employee不能为空");

        if(employee.getId() != null){

            employeeMapper.updateByPrimaryKey(employee);


        }else{

            //设置默认属性
            employee.setAdmin(Employee.IS_ADMIN_NO);
            employee.setState(Employee.IS_STATE_YES);
            //设置录入时间
            employee.setInputTime(new Date());

            //返回受影响的行数
            int row = employeeMapper.insert(employee);

            if(row == 0){

                throw new RuntimeException("添加失败");
            }


        }



    }


    @Override
    public void deleteEmployee(Long id){

        int row = employeeMapper.deleteByPrimaryKey(id);

        if(row == 0){

            throw new RuntimeException("删除失败");
        }

    }

    @Override
    public PageResult queryPage(EmployeeQueryObject e) {


        //分页处理，显示第一页的10条数据
        PageHelper.startPage(e.getPage(),e.getRows());

        List<Employee> list = employeeMapper.selectAll(e);

        // 取分页信息
        PageInfo<Employee> pageInfo = new PageInfo<>(list);

        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

}
