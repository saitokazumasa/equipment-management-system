package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface IEquipmentCategoryMapper {

    @Insert("INSERT INTO equipment_category (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(final EquipmentCategory equipmentCategory);
}
