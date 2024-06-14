package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Sample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ISampleMapper {

//    @Insert("INSERT INTO sample (name, course) " +
//            "VALUES (#{name}, #{course})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    public void insert(final Sample sample);

    @Select("SELECT * FROM sample")
    public List<Sample> selectAll();
}
