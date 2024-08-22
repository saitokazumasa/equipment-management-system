package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ICheckoutApplicationsMapper {
    @Insert("INSERT INTO checkout_applications (equipment_id, account_id) " +
            "VALUES (#{equipmentId}, #{accountId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(final CheckoutApplication checkoutApplication);

    @Select("SELECT * FROM checkout_applications c_apply " +
            "LEFT OUTER JOIN checkout_approvals c_approve ON c_apply.id = c_approve.checkout_application_id " +
            "LEFT OUTER JOIN return_applications r_apply ON c_apply.id = r_apply.checkout_log_id " +
            "LEFT OUTER JOIN return_approvals r_approve ON r_apply.id = r_approve.return_application_id " +
            "WHERE c_approve.checkout_application_id IS NOT NULL " +
            "AND r_approve.return_application_id IS NULL " +
            "AND c_apply.equipment_id = #{equipmentId} " +
            "AND c_apply.account_id = #{accountId} " +
            "ORDER BY r_approve.created_at DESC LIMIT 1")
    public CheckoutApplication selectByEquipmentIdAndAccountId(final int equipmentId, final int accountId);

    @Select("SELECT * FROM checkout_applications " +
            "WHERE checkout_applications.equipment_id = #{equipmentId} " +
            // 貸出申請が承認されている
            "AND EXISTS(" +
            "   SELECT * FROM checkout_approvals c " +
            "   WHERE c.checkout_application_id = checkout_applications.id " +
            ") " +
            // 返却申請を行っていない
            "AND NOT EXISTS(" +
            "   SELECT * FROM return_applications r " +
            "   WHERE r.checkout_application_id = checkout_applications.id" +
            ")")
    public CheckoutApplication selectNotReturnedByEquipmentId(final int equipmentId);
}
