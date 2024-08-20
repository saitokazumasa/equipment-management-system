package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface IStudentsMapper {
    @Insert("INSERT INTO students (id, account_id, course_id, admission_year, graduation_year) " +
            "VALUES (#{id}, #{account_id}, #{course_id}, #{admission_year}, #{graduation_year})")
    public void insert(final Student student);
}
