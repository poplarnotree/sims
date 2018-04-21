package org.ycm.sims.dao;


import org.apache.ibatis.annotations.Param;
import org.ycm.sims.entity.Role;

import java.util.List;

/**
 * Create by yangchangmin
 * 2018/4/14 21:42
 */
public interface RoleDao {

//    Role findRoleById(int id);
    /*根据loginName查询Role*/
    Role findRoleByLoginName(String loginName);

    /*修改密码*/
    int updatePassword(Role role);

    /*创建role*/
    int createRole(Role role);

    /*注销role*/
    int cancelRole(int id);

    /*重置密码*/
    int resetPassword(Role role);

    /*查询角色*/
    List<Role> findRole(Role role);

    /*查询角色记录数*/
    int roleCount(Role role);

}
