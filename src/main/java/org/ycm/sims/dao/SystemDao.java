package org.ycm.sims.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ycm.sims.dto.RecordPageDTO;
import org.ycm.sims.entity.Menu;
import org.ycm.sims.entity.Record;

import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/5/6 15:41
 */
@Mapper
public interface SystemDao {

    /*增加记录*/
    Integer addRecord(Record record);

//    记录数
    Integer recordCount();

//    查询记录
    List<Record> recordList(@Param("id") Integer id);

//    增加菜单
    Integer addMenu(Menu menu);

//    根据菜单id查询菜单
    Menu findMenuById(@Param("id") Integer id);

//    修改菜单
    Integer updateMenu(Menu menu);

//    删除菜单
    Integer deleteMenu(@Param("id") Integer id);

//    菜单数量
    Integer menuCount(@Param("id") Integer id);

//    菜单列表
    List<Menu> menuList(@Param("id") Integer id);

//    菜单树
    List<Menu> menuTree(Menu menu);

}
