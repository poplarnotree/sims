package org.ycm.sims.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * session验证，实现登录验证
 * Create by yangchangmin
 * 2018/4/16 1:35
 */
public class SessionUtil {

    public static String SessionUntil(HttpServletRequest request, String url){
        HttpSession session = request.getSession();
        if (session.getAttribute("loginName") == null){
            return "/role/login";
        }
        return url;
    }

}
