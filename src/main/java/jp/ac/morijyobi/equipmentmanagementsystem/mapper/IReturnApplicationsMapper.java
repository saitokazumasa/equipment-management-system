package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.ReturnApplication;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface IReturnApplicationsMapper {
    @Insert("INSERT INTO return_applications (checkout_application_id) " +
            "VALUES (#{checkoutApplicationId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(final ReturnApplication returnApplication);
}
