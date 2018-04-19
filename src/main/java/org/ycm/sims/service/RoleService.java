package org.ycm.sims.service;


import com.github.pagehelper.PageInfo;
import org.ycm.sims.VO.RoleCheckVO;
import org.ycm.sims.VO.RoleVO;
import org.ycm.sims.dto.RoleDTO;
import org.ycm.sims.dto.UpdatePasswordDTO;
import org.ycm.sims.entity.Role;

/**
 * Create by yangchangmin
 * 2018/4/14 21:50
 */
public interface RoleService {

    RoleCheckVO login(RoleDTO roleDTO);

    RoleCheckVO updatePassword(UpdatePasswordDTO updatePasswordDTO);

    RoleCheckVO createRole(RoleDTO roleDTO);

    RoleCheckVO cancelRole(RoleDTO roleDTO);

    RoleCheckVO resetPassword(RoleDTO roleDTO);

    PageInfo<RoleVO> findRole(RoleDTO roleDTO, int page);
}
