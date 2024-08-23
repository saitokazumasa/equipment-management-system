package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IEquipmentsMapper {
    @Insert("INSERT INTO equipments (name, category_id, storage_location_id, state, lending_period, notification_date, remark) " +
            "VALUES (#{name}, #{categoryId}, #{storageLocationId}, #{state}, #{lendingPeriod}, #{notificationDate}, #{remark})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(final Equipment equipment);

    @Select("SELECT * FROM equipments WHERE id = #{id}")
    public Equipment selectById(final int id);

    @Select("SELECT equipments.* FROM equipments " +
            "LEFT OUTER JOIN checkout_applications ON checkout_applications.equipment_id = equipments.id " +
            "WHERE equipments.id = #{id} " +
            "AND equipments.state = 'ON_LOAN' " +
            // 貸出申請承認が存在する
            "AND EXISTS(" +
            "   SELECT * FROM checkout_approvals c " +
            "   WHERE c.checkout_application_id = checkout_applications.id " +
            ")" +
            // 返却申請が存在しない
            "AND NOT EXISTS(" +
            "   SELECT * FROM return_applications r " +
            "   WHERE r.checkout_application_id = checkout_applications.id" +
            ")")
    public Equipment selectOnLoanById(final int id);

    // NOTE: ここでは 返却されていない かつ state が ON_LOAN の物も含まれる
    @Select("SELECT equipments.* FROM equipments " +
            "LEFT OUTER JOIN checkout_applications ON checkout_applications.equipment_id = equipments.id " +
            "WHERE equipments.id = #{id} " +
            "AND equipments.state <> 'NOT_AVAILABLE_FOR_LOAN' " +
            // 貸出申請が存在しない
            "AND (" +
            "   checkout_applications.id IS NULL " +
            // 貸出申請承認が存在する (貸出申請中の物は除外する)
            "   OR EXISTS(" +
            "       SELECT * FROM checkout_approvals c " +
            "       WHERE c.checkout_application_id = checkout_applications.id" +
            "   )" +
            ")" +
            "ORDER BY checkout_applications.created_at DESC " +
            "LIMIT 1")
    public Equipment selectAvailableForLoanById(final int id);

    @Select("<script>" +
            "SELECT * FROM equipments WHERE name LIKE CONCAT('%', #{name}, '%') " +
            "AND category_id IN (<foreach collection='categoryIdList' item='categoryId' separator=','>#{categoryId}</foreach>) " +
            "AND state IN (<foreach collection='stateList' item='state' separator=','>#{state}</foreach>) " +
            "ORDER BY id" +
            "</script>"
    )
    public List<Equipment> selectBySearchCriteria(
            final String name,
            final List<Integer> categoryIdList,
            final List<EquipmentState> stateList
    );

    @Select("SELECT * FROM equipments ORDER BY id")
    public List<Equipment> selectAll();

    @Update("UPDATE equipments SET state = #{state} WHERE id = #{id}")
    public int updateState(final int id, final EquipmentState state);
}
