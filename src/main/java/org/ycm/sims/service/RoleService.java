package org.ycm.sims.service;


import com.github.pagehelper.PageInfo;
import org.ycm.sims.VO.RoleCheckVO;
import org.ycm.sims.dto.RoleDTO;
import org.ycm.sims.entity.Role;

/**
 * Create by yangchangmin
 * 2018/4/14 21:50
 */
public interface RoleService {

    RoleCheckVO login(RoleDTO roleDTO);

    RoleCheckVO updatePassword(String originalPassword, String newPassword);

    RoleCheckVO createRole(RoleDTO roleDTO);

    RoleCheckVO cancelRole(int id, int roleType);

    RoleCheckVO resetPassword(int id, int roleType);

    PageInfo<Role> findRole(RoleDTO roleDTO, int page);
}
