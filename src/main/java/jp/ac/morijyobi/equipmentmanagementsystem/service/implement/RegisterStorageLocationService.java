package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.StorageLocation;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IStorageLocationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IRegisterStorageLocationService;
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
        // NOTE: MyBatis により newValue.id がオートインクリメント値に書き変わっている
        this.storageLocationsMapper.insert(newValue);
        return newValue;
    }
}
