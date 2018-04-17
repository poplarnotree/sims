package org.ycm.sims.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangchangmin
 * on 2018/4/17 16:51
 */
@Slf4j
public class SessionUtil extends RequestContextHolder {
    private static String SESSION_LOGIN_NAME = "loginName";

    public static String getRequest(HttpServletRequest request) {
        String loginName = (String) request.getSession().getAttribute(SESSION_LOGIN_NAME);
        return loginName;
    }
}
