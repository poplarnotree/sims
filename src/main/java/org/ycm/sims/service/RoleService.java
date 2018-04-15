package org.ycm.sims.service;


import org.ycm.sims.dto.CheckDTO;
import org.ycm.sims.dto.LoginDTO;
import org.ycm.sims.entity.Role;

/**
 * Create by yangchangmin
 * 2018/4/14 21:50
 */
public interface RoleService {
    Role findRoleByLoginName(String loginName);

    LoginDTO login(String loginName, String loginPassword);
}
