package org.ycm.sims.service;


import org.ycm.sims.VO.RoleVO;

/**
 * Create by yangchangmin
 * 2018/4/14 21:50
 */
public interface RoleService {

    RoleVO login(String loginName, String loginPassword);

    RoleVO updatePassword(String originalPassword, String newPassword);

    RoleVO createRole(String loginName, String loginPassword, int roleType);

    RoleVO cancelRole(int id, int roleType);

    RoleVO resetPassword(int id, int roleType);
}
