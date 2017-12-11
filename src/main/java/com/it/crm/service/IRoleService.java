package com.it.crm.service;

import com.it.crm.domain.Permission;
import com.it.crm.domain.Role;
import com.it.crm.page.RoleQueryObject;
import com.it.crm.vo.PageResult;

import java.util.List;

/**
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-10 19:06
 */
public interface IRoleService {
    /**
     *  添加角色
     * @param r
     */
    void insert(Role r);

    /**
     * 更新角色信息
     * @param r
     */
    void update(Role r);

    /**
     * 查询所有角色id和name
     * @return
     */
    PageResult queryALl(RoleQueryObject r);

    /**
     * 根据id查询role对应的权限
     * @param id
     * @return
     */
    List<Permission> queryPermissionById(Long id);
}
