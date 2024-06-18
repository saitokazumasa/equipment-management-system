package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IAccountsMapper {

    @Insert("INSERT INTO accounts (name, mail, password, salt, category, state) " +
            "VALUES (#{name}, #{mail}, #{password}, #{salt}, #{category}, #{state})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(final Account account);

    @Select("SELECT * FROM accounts WHERE mail = #{mail}")
    public Account selectByMail(final String mail);
}
