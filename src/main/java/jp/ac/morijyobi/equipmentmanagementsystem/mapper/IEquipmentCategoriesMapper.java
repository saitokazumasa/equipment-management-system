package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IEquipmentCategoriesMapper {
    @Insert("INSERT INTO equipment_categories (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(final EquipmentCategory equipmentCategory);

    @Select("SELECT * FROM equipment_categories WHERE name = #{name}")
    public EquipmentCategory selectByName(final String name);

    @Select("SELECT * FROM equipment_categories ORDER BY id")
    public List<EquipmentCategory> selectAll();
}
