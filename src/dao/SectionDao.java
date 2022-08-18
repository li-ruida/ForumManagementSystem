package dao;

import model.Section;
import model.User;

import java.sql.Connection;
import java.util.List;

/**
 * @author lrd
 * @date 2022-08-18 上午10:55
 */
public interface SectionDao {
    List<Section> getSection();//获取User类

    void saveSection(Connection conn,Section section);//插入User

    void deleteSectionById(Connection conn,int sectionid);//删除User

    Section getSectionById(Connection conn,int sectionId);//根据Id查

    void updateSection(Connection conn,Section section);//更新信息
}
