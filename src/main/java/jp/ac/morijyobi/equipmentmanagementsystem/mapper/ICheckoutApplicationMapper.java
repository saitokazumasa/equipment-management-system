package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ICheckoutApplicationMapper {
    @Insert("insert into checkout_applications (equipment_id, account_id) " +
            "values (#{equipmentId}, #{accountId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(final CheckoutApplication checkoutApplication);
}
