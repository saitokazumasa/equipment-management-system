package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.StorageLocation;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IStorageLocationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IListStorageLocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListStorageLocationService implements IListStorageLocationService {
    private final IStorageLocationsMapper storageLocationsMapper;

    public ListStorageLocationService(final IStorageLocationsMapper storageLocationsMapper) {
        this.storageLocationsMapper = storageLocationsMapper;
    }

    @Override
    public List<StorageLocation> execute() {
        return storageLocationsMapper.selectAll();
    }
}
