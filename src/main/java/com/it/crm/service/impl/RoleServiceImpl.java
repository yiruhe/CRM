package com.it.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.crm.domain.Permission;
import com.it.crm.domain.Role;
import com.it.crm.mapper.RoleMapper;
import com.it.crm.page.RoleQueryObject;
import com.it.crm.service.IRoleService;
import com.it.crm.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-10 19:05
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;




    @Override
    public void insert(Role r){


        //添加role
        roleMapper.insert(r);


        createRelation(r);

    }


    @Override
    public void update(Role r){

        if(r.getId() !=null){

            roleMapper.updateByPrimaryKey(r);

            //删除所有的权限
            roleMapper.delAllPermissionById(r.getId());

            //重新建立权限关系
            createRelation(r);
        }
    }


    //建立关系
    private void createRelation(Role r){

        //建立关系
        int len = r.getPermission().size();

        for(Permission p:r.getPermission()){

            roleMapper.createRelation(p.getId(),r.getId());
        }

    }


    @Override
    public PageResult queryALl(RoleQueryObject r){

        PageHelper.startPage(r.getPage(),r.getRows());

         List<Role> role = roleMapper.selectAll(r);

        PageInfo<Role> pageInfo = new PageInfo<>(role);

        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }


    @Override
    public List<Permission> queryPermissionById(Long id){


        if(id != null){

           Role role =  roleMapper.queryPermissionById(id);

           if(role != null){

                return role.getPermission();
           }

        }

        return Collections.EMPTY_LIST;
    }

}
