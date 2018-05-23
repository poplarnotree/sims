package org.ycm.sims.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ycm.sims.VO.HeadVO;
import org.ycm.sims.dao.InformationDao;
import org.ycm.sims.dao.RoleDao;
import org.ycm.sims.entity.Role;
import org.ycm.sims.entity.StudentInformation;
import org.ycm.sims.entity.TeacherInformation;
import org.ycm.sims.service.IndexService;
import org.ycm.sims.utils.FormatConversionUtil;
import org.ycm.sims.utils.SessionUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by yangchangmin
 * on 2018/5/10 0:12
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private InformationDao informationDao;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RoleDao roleDao;

    @Override
    public HeadVO roleName() {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        String name = null;
        if (role.getRoleType() == 1){
            name = informationDao.findByInformation(new TeacherInformation(role.getLoginName())).getName();
        }
        if (role.getRoleType() == 2){
            name = informationDao.findStudentInformation(new StudentInformation(role.getLoginName())).getName();
        }
        String type = FormatConversionUtil.typeFormatUitl(role.getRoleType());
        return new HeadVO(name, type);
    }
}
