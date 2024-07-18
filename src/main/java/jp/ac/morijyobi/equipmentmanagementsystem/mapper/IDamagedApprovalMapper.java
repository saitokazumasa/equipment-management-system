package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.DamagedApproval;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface IDamagedApprovalMapper {
    @Insert("INSERT INTO damaged_approvals (damaged_application_id, account_id) " +
            "VALUES (#{damagedApplicationId}, #{accountId}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(final DamagedApproval damagedApproval);
}
