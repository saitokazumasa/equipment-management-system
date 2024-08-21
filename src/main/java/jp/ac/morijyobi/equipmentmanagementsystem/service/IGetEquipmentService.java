package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;

public interface IGetEquipmentService {
    public Equipment executeById(final int id);
}
