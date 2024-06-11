package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICourseMapper {

    @Insert("INSERT INTO courses (name) " +
            "VALUES (#{name})")
    public void insert(final Course course);
}
