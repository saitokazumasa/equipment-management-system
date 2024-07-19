package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IEquipmentsMapper {
    @Insert("INSERT INTO equipments (name, category_id, storage_location_id, state, lending_period, notification_date, remark) " +
            "VALUES (#{name}, #{categoryId}, #{storageLocationId}, #{state}, #{lendingPeriod}, #{notificationDate}, #{remark})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(final Equipment equipment);

    @Select("SELECT * FROM equipments WHERE id = #{id} ORDER BY id")
    public Equipment selectById(final int id);

    @Select("SELECT * FROM equipments ORDER BY id")
    public List<Equipment> selectAll();

    @Select("SELECT DISTINCT ON (e.id) e.* FROM equipments e " +
            "LEFT OUTER JOIN checkout_applications c_apply ON c_apply.equipment_id = e.id " +
            "LEFT OUTER JOIN checkout_approvals c_approve ON c_approve.checkout_application_id = c_apply.id " +
            "LEFT OUTER JOIN return_applications r_apply ON r_apply.checkout_log_id = c_apply.id " +
            "LEFT OUTER JOIN return_approvals r_approve ON r_approve.return_application_id = c_apply.id " +
            "WHERE e.state = 'ON_LOAN' " +
            "AND r_approve.return_application_id IS NULL " +
            "ORDER BY e.id, c_approve.created_at DESC" )
    public List<Equipment> selectLending();
}
