package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.StorageLocation;

public interface IGetStorageLocationService {
    public StorageLocation executeById(final int id);
}
