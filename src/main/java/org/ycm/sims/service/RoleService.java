package org.ycm.sims.service;


import org.ycm.sims.VO.RoleVO;
import org.ycm.sims.dto.RoleDTO;
import org.ycm.sims.entity.Role;

import java.util.List;

/**
 * Create by yangchangmin
 * 2018/4/14 21:50
 */
public interface RoleService {

    RoleVO login(RoleDTO roleDTO);

    RoleVO updatePassword(String originalPassword, String newPassword);

    RoleVO createRole(RoleDTO roleDTO);

    RoleVO cancelRole(int id, int roleType);

    RoleVO resetPassword(int id, int roleType);

    List<Role> findRole(RoleDTO roleDTO);
}
