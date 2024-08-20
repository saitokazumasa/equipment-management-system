package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ICoursesMapper {
    @Insert("INSERT INTO courses (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(final Course course);

    @Select("SELECT * FROM courses")
    public List<Course> selectAll();
}
