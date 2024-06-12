package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICheckoutApplicationMapper {
    @Insert("insert into checkout_applications (equipment_id, account_id) " +
            "values (#{equipmentId}, #{accountId})")
    public void insert(final CheckoutApplication checkoutApplication);
}
