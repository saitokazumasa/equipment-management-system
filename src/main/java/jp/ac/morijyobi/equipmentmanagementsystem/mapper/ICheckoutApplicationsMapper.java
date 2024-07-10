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

    @Select("SELECT ca.* from checkout_applications ca " +
            "LEFT OUTER JOIN checkout_approvals ca2 on ca2.checkout_application_id = ca.id " +
            "LEFT OUTER JOIN return_applications ra on ra.checkout_log_id = ca.id " +
            "LEFT OUTER JOIN return_approvals ra2 on ra2.return_application_id = ra.id " +
            "WHERE ca.account_id = #{accountId} " +
            "AND ca.equipment_id = #{equipmentId} " +
            "AND ca2.checkout_application_id = ca.id " +
            "AND ra.checkout_log_id IS NULL " +
            "OR ra2.return_application_id IS NULL " +
            "ORDER BY ca.id ASC")
    public CheckoutApplication selectNotReturned(final int accountId, final int equipmentId);
}