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
    public void insert(final CheckoutApplication checkoutApplication);

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

    @Select("SELECT * FROM checkout_applications WHERE id = #{id}")
    public CheckoutApplication selectById(final int id);

    // 配列に含まれているIDの申請を取得
    @Select({
            "<script>",
            "SELECT * FROM checkout_applications WHERE id IN",
            "<foreach item='id' collection='ids' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    public List<CheckoutApplication> selectByIds(final List<Integer> ids);
}
