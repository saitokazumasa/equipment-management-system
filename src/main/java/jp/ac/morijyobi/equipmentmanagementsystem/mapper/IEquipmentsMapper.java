package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
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
}
