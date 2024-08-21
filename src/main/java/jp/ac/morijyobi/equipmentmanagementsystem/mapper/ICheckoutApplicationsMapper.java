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
    public void insert(final CheckoutApplication checkoutApplication);

    @Select("SELECT * FROM checkout_applications c_application " +
            "WHERE c_application.equipment_id = #{equipmentId} " +
            // 貸出申請が承認されている
            "AND EXISTS(" +
            "   SELECT * FROM checkout_approvals c_approval " +
            "   WHERE c_approval.checkout_application_id = c_application.id " +
            "   AND c_approval.created_at > c_application.created_at" +
            ") " +
            // 返却申請を行っていない
            "AND NOT EXISTS(" +
            "   SELECT * FROM return_applications r " +
            "   WHERE r.checkout_application_id = c_application.id" +
            ")")
    public CheckoutApplication selectNotReturnedByEquipmentId(final int equipmentId);
}
