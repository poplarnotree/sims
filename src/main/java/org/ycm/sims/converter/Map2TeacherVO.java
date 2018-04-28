package org.ycm.sims.converter;

import org.ycm.sims.VO.TeacherVO;

import java.util.List;
import java.util.Map;

/**
 * 数据库查到的List集合转TeacherVO
 * Created by yangchangmin
 * on 2018/4/28 17:38
 */
public class Map2TeacherVO {

    public static TeacherVO map2TeacherVO(List<Map<String, String>> list){
        TeacherVO teacherVO = new TeacherVO();
        for (Map<String, String> map: list){
            String subject = map.get("subject");
            String name = map.get("name");
            switch (subject){
                case "语文":
                    teacherVO.setChinese(name);
                    break;
                case "数学":
                    teacherVO.setMath(name);
                    break;
                case "英语":
                    teacherVO.setEnglish(name);
                    break;
                case "物理":
                    teacherVO.setPhysics(name);
                    break;
                case "化学":
                    teacherVO.setChemistry(name);
                    break;
                case "生物":
                    teacherVO.setBiology(name);
                    break;
                case "政治":
                    teacherVO.setPolitics(name);
                    break;
                case "地理":
                    teacherVO.setGeography(name);
                    break;
                case "历史":
                    teacherVO.setHistory(name);
                    break;
            }
        }
        return teacherVO;
    }

}
