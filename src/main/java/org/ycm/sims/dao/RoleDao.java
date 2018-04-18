package org.ycm.sims.dao;


import org.apache.ibatis.annotations.Param;
import org.ycm.sims.entity.Role;

/**
 * Create by yangchangmin
 * 2018/4/14 21:42
 */
public interface RoleDao {

//    Role findRoleById(int id);

    Role findRoleByLoginName(String loginName);

    int updatePassword(@Param("loginName") String loginName,
                       @Param("loginPassword") String loginPassword);

    int createRole(Role role);

    int cancelRole(int id);

    int resetPassword(@Param("id")int id,
                      @Param("loginPassword")String loginPassword);


}
