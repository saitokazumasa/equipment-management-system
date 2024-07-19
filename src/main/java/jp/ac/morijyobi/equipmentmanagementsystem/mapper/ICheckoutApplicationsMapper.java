package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ICheckoutApplicationsMapper {
    @Insert("INSERT INTO checkout_applications (equipment_id, account_id) " +
            "VALUES (#{equipmentId}, #{accountId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(final CheckoutApplication checkoutApplication);

    @Select("SELECT c_apply.account_id FROM checkout_applications c_apply " +
            "LEFT OUTER JOIN checkout_approvals c_approve on c_approve.checkout_application_id = c_apply.id " +
            "WHERE c_apply.equipment_id = #{equipmentId} " +
            "AND c_approve.checkout_application_id IS NOT NULL " +
            "ORDER BY c_approve.created_at DESC " +
            "LIMIT 1")
    public int selectByEquipmentId(final int equipmentId);

    @Select("SELECT c_apply.* from checkout_applications c_apply " +
            "LEFT OUTER JOIN checkout_approvals c_approve on c_approve.checkout_application_id = c_apply.id " +
            "LEFT OUTER JOIN return_applications r_apply on r_apply.checkout_log_id = c_apply.id " +
            "LEFT OUTER JOIN return_approvals r_approve on r_approve.return_application_id = r_apply.id " +
            "WHERE c_apply.account_id = #{accountId} " +
            "AND c_apply.equipment_id = #{equipmentId} " +
            "AND c_approve.checkout_application_id = c_apply.id " +
            "OR r_approve.return_application_id IS NULL " +
            "ORDER BY c_apply.created_at DESC " +
            "LIMIT 1")
    public CheckoutApplication selectNotReturned(final int accountId, final int equipmentId);
}