package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ICoursesMapper {

    @Insert("INSERT INTO courses (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(final Course course);
}
