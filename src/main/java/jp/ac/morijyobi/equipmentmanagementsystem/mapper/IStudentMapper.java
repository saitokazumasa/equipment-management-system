package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface IStudentMapper {

    @Insert("INSERT INTO students (account_id, course_id, admission_year, graduation_year) " +
            "VALUES (#{account_id}, #{course_id}, #{admission_year}, #{graduation_year})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(final Student student);
}
