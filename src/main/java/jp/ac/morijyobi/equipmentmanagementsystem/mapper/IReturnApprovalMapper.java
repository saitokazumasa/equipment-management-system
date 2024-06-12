package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.ReturnApproval;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface IReturnApprovalMapper {
    @Insert("INSERT INTO return_approvals (return_application_id, account_id) " +
            "VALUES (#{returnApplicationId}, #{accountId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(final ReturnApproval returnApproval);
}
