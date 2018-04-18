package org.ycm.sims.utils;

import org.ycm.sims.dao.RoleDao;
import org.ycm.sims.entity.Role;
import org.ycm.sims.enums.ExceptionEnum;
import org.ycm.sims.enums.ParameterEnum;
import org.ycm.sims.exception.SimsException;

import javax.servlet.http.HttpServletRequest;

/**
 * session判断
 * Created by yangchangmin
 * on 2018/4/18 17:42
 */
public class SessionUtil {

    public static Role LoginNameCheckSession(HttpServletRequest request, RoleDao roleDao){
        String sessionLoginName = (String) request.getSession().getAttribute(ParameterEnum.LOGIN_NAME.getValue());
        Role role = roleDao.findRoleByLoginName(sessionLoginName);
        if (role == null){
            throw new SimsException(ExceptionEnum.SYSTEM_ERROR);
        }
        return role;
    }
}
