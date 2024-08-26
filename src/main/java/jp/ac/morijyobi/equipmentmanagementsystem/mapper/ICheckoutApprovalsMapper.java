package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApproval;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ICheckoutApprovalsMapper {
    @Insert("INSERT INTO checkout_approvals (checkout_application_id, account_id) " +
            "VALUES (#{checkoutApplicationId}, #{accountId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(final CheckoutApproval checkoutApproval);

    @Select("SELECT * FROM checkout_approvals WHERE checkout_application_id = #{id}")
    public CheckoutApproval selectByCheckoutApplicationId(final int id);
}
