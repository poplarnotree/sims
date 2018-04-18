package org.ycm.sims.utils;

import org.ycm.sims.enums.ParameterEnum;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangchangmin
 * on 2018/4/18 10:23
 */
public class ControllerJumpUtil {

    public static String ControllerJumpUtil(HttpServletRequest request, String adminUrl, String url){
        int roleType = (int)request.getSession().getAttribute(ParameterEnum.ROLE_TYPE.getValue());
        if (roleType == 0){
            return adminUrl;
        }
        if (roleType == 1 || roleType == 2){
            return url;
        }else {
            request.getSession().invalidate();
            return "/role/login";
        }
    }
}
