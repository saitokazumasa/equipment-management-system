package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.StorageLocation;

public interface IRegisterStorageLocationService {
    /** 既に登録されている保管場所の場合は重複登録されない */
    public StorageLocation execute(final String name);
}
