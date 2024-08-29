package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.StorageLocation;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IStorageLocationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IGetStorageLocationService;
import org.springframework.stereotype.Service;

@Service
public class GetStorageLocationService implements IGetStorageLocationService {
    private final IStorageLocationsMapper storageLocationsMapper;

    public GetStorageLocationService(IStorageLocationsMapper storageLocationsMapper) {
        this.storageLocationsMapper = storageLocationsMapper;
    }

    @Override
    public StorageLocation executeById(final int id) {
        return storageLocationsMapper.selectById(id);
    }
}
