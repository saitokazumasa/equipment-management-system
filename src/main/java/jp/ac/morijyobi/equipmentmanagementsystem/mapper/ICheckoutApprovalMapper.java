package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApproval;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface ICheckoutApprovalMapper {
    @Insert("INSERT INTO checkout_approvals (checkout_application_id, account_id) " +
            "VALUES (#{checkoutApplicationId}, #{accountId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(final CheckoutApproval checkoutApproval);
}
