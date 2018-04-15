package org.ycm.sims.dao;


import org.ycm.sims.entity.Role;

/**
 * Create by yangchangmin
 * 2018/4/14 21:42
 */
public interface RoleDao {

    Role findRoleByLoginName(String loginName);

//    Boolean createTeacheRole();
}
