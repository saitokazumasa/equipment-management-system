package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.StorageLocation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface IStorageLocationMapper {
    @Insert("INSERT INTO storage_location (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(final StorageLocation storageLocation);
}
