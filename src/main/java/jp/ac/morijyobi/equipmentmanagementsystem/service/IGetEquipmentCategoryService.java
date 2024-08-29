package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;

public interface IGetEquipmentCategoryService {
    public EquipmentCategory executeById(final int id);
}
