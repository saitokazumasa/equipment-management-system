package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;

public interface IRegisterEquipmentCategoryService {
    /** 既に登録されている保管場所の場合は重複登録されない */
    public EquipmentCategory execute(final String name);
}
