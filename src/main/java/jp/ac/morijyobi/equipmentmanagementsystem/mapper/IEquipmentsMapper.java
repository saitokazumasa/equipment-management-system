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
    public void insert(final Equipment equipment);

    @Select("SELECT * FROM equipments WHERE id = #{id} ORDER BY id ASC")
    public Equipment selectById(final int id);

    @Select("SELECT * FROM equipments ORDER BY id ASC")
    public List<Equipment> selectAll();
}
