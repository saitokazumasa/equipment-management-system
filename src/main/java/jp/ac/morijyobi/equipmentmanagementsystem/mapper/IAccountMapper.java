package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface IAccountMapper {

    @Insert("INSERT INTO account (name, mail, password, salt, category, state) " +
            "VALUES (#{name}, #{mail}, #{password}, #{salt}, #{category}, #{state})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(final Account account);

}
