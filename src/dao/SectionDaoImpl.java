package dao;

import model.Section;
import util.JDBCUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author lrd
 * @date 2022-08-18 下午4:09
 */
public class SectionDaoImpl extends BaseDao<Section>implements SectionDao{
    @Override
    public List<Section> getSection() {
        String sql="select * from `section`";
        List beanList = BaseDao.getForList(Section.class, sql);
        return beanList;
    }

    @Override
    public void saveSection(Connection conn, Section section) {
        String sql="insert into `section` VALUES (?,?,?)";
        BaseDao.update(conn,sql,section.getSectionIds(),section.getUserIds(),section.getSecName());
    }

    @Override
    public void deleteSectionById(Connection conn, int sectionid) {
        String sql="delete from `section` where  `SectionIds` = ?";
        BaseDao.update(conn,sql,sectionid);
    }

    @Override
    public Section getSectionById(Connection conn, int sectionId) {
        String sql="select * from `section` where  `SectionIds` = ?";
        return BaseDao.getInstance(Section.class,sql,sectionId);
    }

    @Override
    public void updateSection(Connection conn, Section section) {
        String sql="update `section` set `UserIds`=?, `SecName`=? where `SectionIds` =?";
        BaseDao.update(conn,sql,section.getUserIds(),section.getSecName(),section.getSectionIds());

    }

    public static Long countnum(){
        Connection conn= JDBCUtils.getConnection();
        String sql="select count(*) from ourforum.section;";
        Long value = BaseDao.<Long>getValue(conn, sql);
        return value;
    }
}
