package org.ycm.sims.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ycm.sims.VO.AchievementVO;
import org.ycm.sims.VO.CheckVO;
import org.ycm.sims.VO.PageVO;
import org.ycm.sims.dao.AchievementDao;
import org.ycm.sims.dao.InformationDao;
import org.ycm.sims.dao.RoleDao;
import org.ycm.sims.dto.AchievementDTO;
import org.ycm.sims.dto.AchievementPageDTO;
import org.ycm.sims.entity.*;
import org.ycm.sims.enums.ExceptionEnum;
import org.ycm.sims.enums.ResultEnum;
import org.ycm.sims.exception.SimsException;
import org.ycm.sims.pojo.AchievementPojo;
import org.ycm.sims.service.AchievementService;
import org.ycm.sims.service.SystemService;
import org.ycm.sims.utils.FormatConversionUtil;
import org.ycm.sims.utils.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/5/24 22:26
 */
@Service
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    private AchievementDao achievementDao;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private InformationDao informationDao;

    @Autowired
    private SystemService systemService;

    /**
     * 录入学生成绩
     * @param achievementDTO
     * @return
     */
    @Override
    @Transactional
    public CheckVO addAchievement(AchievementDTO achievementDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (role.getRoleType() == 1){
            Achievement achievement = new Achievement();
            BeanUtils.copyProperties(achievementDTO, achievement);
            TeacherInformation teacherInformation = informationDao.findByInformation(new TeacherInformation(role.getLoginName()));
            int tInformationId = teacherInformation.getId();
            String classes = informationDao.findStudentInformation(new StudentInformation(achievementDTO.getSInformationId())).getClasses();
            List<Classes> teacherClassList = informationDao.findTeaClassCount(role.getLoginName(), "");
            for (int i = 0; i < teacherClassList.size(); i++){
                Classes classes1 = teacherClassList.get(i);
                if (!classes.equals(classes1.getName())){
                    if (i == teacherClassList.size()-1){
                        request.getSession().invalidate();
                        throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
                    }
                }else{
                    break;
                }
            }
            String subject = teacherInformation.getSubject();
            achievement.setTInformationId(tInformationId);
            achievement.setClasses(classes);
            achievement.setSubject(subject);
            achievement.setStatus(0);
            achievement.setCreateId(role.getId());
            int row = achievementDao.insertAchievement(achievement);
            if (row == 1){
                return new CheckVO(ResultEnum.SUCCESS);
            }
            throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
        }else{
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    @Override
    public PageVO<AchievementVO> achievementPage(AchievementPageDTO achievementPageDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (role.getRoleType() == 0){
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
        PageHelper.startPage(achievementPageDTO.getPage(), achievementPageDTO.getLimit());
        List<AchievementPojo> achievementPojoList = achievementDao.findAchievement(achievementPageDTO);
        List<AchievementVO> achievementVOList = new ArrayList<>();
        int count = achievementPojoList.size();
        for (AchievementPojo achievementPojo : achievementPojoList){
            AchievementVO achievementVO = new AchievementVO();
            BeanUtils.copyProperties(achievementPojo, achievementVO);
            achievementVO.setCreateTime(FormatConversionUtil.DateFormatUtil(achievementPojo.getCreateTime()));
            achievementVOList.add(achievementVO);
        }
        PageVO<AchievementVO> achievementVOPageVO = new PageVO(ResultEnum.SUCCESS, count, achievementVOList);
        return achievementVOPageVO;
    }
}
