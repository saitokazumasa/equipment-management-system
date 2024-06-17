package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EditLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface IEditLogMapper {

    @Insert("INSERT INTO edit_logs (equipment_id, account_id, reason, before_equipment_data, after_equipment_data) " +
            "VALUES (#{equipmentId}, #{accountId}, #{reason}, #{beforeEquipmentData}, #{afterEquipmentData})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(final EditLog editLog);
}
