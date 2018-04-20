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

    Role findRoleByLoginName(String loginName);

    int updatePassword(Role role);

    int createRole(Role role);

    int cancelRole(int id);

    int resetPassword(Role role);

    List<Role> findRole(Role role);

    int roleCount(Role role);

}
