package jp.ac.morijyobi.equipmentmanagementsystem.service.equipment;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;

public interface IFetchEquipmentService {
    public Equipment executeById(final int id);
}
