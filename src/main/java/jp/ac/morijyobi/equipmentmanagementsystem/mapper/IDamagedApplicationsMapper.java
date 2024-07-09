package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.DamagedApplication;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface IDamagedApplicationsMapper {
    @Insert("INSERT INTO damaged_applications (checkout_application_id, reason, category) " +
            "VALUES (#{checkoutApplicationId}, #{reason}, #{category})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(final DamagedApplication damagedApplication);
}
