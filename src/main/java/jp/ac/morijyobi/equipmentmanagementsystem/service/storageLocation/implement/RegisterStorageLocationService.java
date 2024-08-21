package jp.ac.morijyobi.equipmentmanagementsystem.service.storageLocation.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.StorageLocation;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IStorageLocationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.storageLocation.IRegisterStorageLocationService;
import org.springframework.stereotype.Service;

@Service
public class RegisterStorageLocationService implements IRegisterStorageLocationService {
    private final IStorageLocationsMapper storageLocationsMapper;

    public RegisterStorageLocationService(final IStorageLocationsMapper storageLocationsMapper) {
        this.storageLocationsMapper = storageLocationsMapper;
    }

    @Override
    public StorageLocation execute(final String name) {
        final StorageLocation value = this.storageLocationsMapper.selectByName(name);
        if (value != null) return value;

        final var newValue = new StorageLocation(-1, name);
        this.storageLocationsMapper.insert(newValue);
        return newValue;
    }
}
