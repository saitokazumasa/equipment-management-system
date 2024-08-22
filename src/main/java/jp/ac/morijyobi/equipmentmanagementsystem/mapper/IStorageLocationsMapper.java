package jp.ac.morijyobi.equipmentmanagementsystem.mapper;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.StorageLocation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IStorageLocationsMapper {
    @Insert("INSERT INTO storage_locations (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(final StorageLocation storageLocation);

    @Select("SELECT * FROM storage_locations WHERE name = #{name}")
    public StorageLocation selectByName(final String name);

    @Select("SELECT * FROM storage_locations ORDER BY id")
    public List<StorageLocation> selectAll();
}
