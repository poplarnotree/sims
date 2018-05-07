package org.ycm.sims.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ycm.sims.VO.*;
import org.ycm.sims.dao.RoleDao;
import org.ycm.sims.dao.SystemDao;
import org.ycm.sims.dto.MenuDTO;
import org.ycm.sims.dto.MenuPageDTO;
import org.ycm.sims.dto.RecordDTO;
import org.ycm.sims.dto.RecordPageDTO;
import org.ycm.sims.entity.Menu;
import org.ycm.sims.entity.Record;
import org.ycm.sims.entity.Role;
import org.ycm.sims.enums.ExceptionEnum;
import org.ycm.sims.enums.ResultEnum;
import org.ycm.sims.enums.TableEnum;
import org.ycm.sims.exception.SimsException;
import org.ycm.sims.service.SystemService;
import org.ycm.sims.utils.CompareDataUtil;
import org.ycm.sims.utils.FormatConversionUtil;
import org.ycm.sims.utils.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/5/6 16:50
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SystemDao systemDao;

    @Autowired
    private SystemService systemService;

    @Override
    @Transactional
    public void addRecord(RecordDTO recordDTO) {
        try {
            Record record = new Record();
            BeanUtils.copyProperties(recordDTO, record);
            if (recordDTO.getUpdateColumn() == null){
                record.setType(1);
            }
            Integer row = systemDao.addRecord(record);
            if (row != 1){
                throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
        }
    }

    @Override
    public PageVO<RecordVO> recordPage(RecordPageDTO recordPageDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (role.getRoleType() == 0) {
            int count = systemDao.recordCount();
            PageHelper.startPage(recordPageDTO.getPage(), recordPageDTO.getLimit());
            List<Record> recordList = systemDao.recordList(recordPageDTO.getId());
            List<RecordVO> recordVOList = new ArrayList<>();
            for (Record record : recordList) {
                RecordVO recordVO = new RecordVO();
                BeanUtils.copyProperties(record, recordVO);
                recordVO.setCreateTime(FormatConversionUtil.DateFormatUtil(record.getCreateTime()));
                recordVOList.add(recordVO);
            }
            PageVO<RecordVO> recordVOPageVO = new PageVO<>(ResultEnum.SUCCESS, count, recordVOList);
            return recordVOPageVO;
        } else {
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    @Override
    @Transactional
    public CheckVO addMenu(MenuDTO menuDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (role.getRoleType() == 0) {
            Menu menu = new Menu();
            BeanUtils.copyProperties(menuDTO, menu);
            menu.setCreateId(role.getId());
            int row = systemDao.addMenu(menu);
            if (row == 1){
                return new CheckVO(ResultEnum.SUCCESS);
            }
            throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
        } else {
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    @Override
    public CheckVO updateMenu(MenuDTO menuDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (role.getRoleType() == 0) {
            Menu menu = new Menu();
            Menu menuData = systemDao.findMenuById(menuDTO.getId());
            BeanUtils.copyProperties(menuDTO, menu);
            int row = systemDao.updateMenu(menu);
            if (row == 1){
                List<RecordDTO> recordDTOList = CompareDataUtil.CompareMenuDataUtil(menuData, menu);
                for (RecordDTO recordDTO : recordDTOList){
                    recordDTO.setLoginName(role.getLoginName());
                    recordDTO.setTableName(TableEnum.MENU.getValue());
                    recordDTO.setKeyId(menuData.getId());
                    systemService.addRecord(recordDTO);
                }
                return new CheckVO(ResultEnum.SUCCESS);
            }
            throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
        } else {
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    @Override
    public CheckVO deleteMenu(Integer id) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (role.getRoleType() == 0) {
            String name = systemDao.findMenuById(id).getName();
            int row = systemDao.deleteMenu(id);
            if (row == 1){
                systemService.addRecord(new RecordDTO(role.getLoginName(), TableEnum.MENU.getValue(), id, name));
                return new CheckVO(ResultEnum.SUCCESS);
            }
            throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
        } else {
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    @Override
    public PageVO<MenuVO> menuPage(MenuPageDTO menuPageDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (role.getRoleType() == 0){
            int count = systemDao.menuCount(menuPageDTO.getId());
            Menu menu = new Menu();
            BeanUtils.copyProperties(menuPageDTO, menu);
            PageHelper.startPage(menuPageDTO.getPage(), menuPageDTO.getLimit());
            List<Menu> menuList = systemDao.menuList(menuPageDTO.getId());
            List<MenuVO> menuVOList = new ArrayList<>();
            for (Menu m : menuList){
                MenuVO menuVO = new MenuVO();
                BeanUtils.copyProperties(m, menuVO);
                String createName = roleDao.findRoleById(role.getId()).getLoginName();
                menuVO.setCreateName(createName);
                menuVO.setCreateTime(FormatConversionUtil.DateFormatUtil(m.getCreateTime()));
                menuVOList.add(menuVO);
            }
            return new PageVO<MenuVO>(ResultEnum.SUCCESS, count, menuVOList);
        } else {
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }
}
