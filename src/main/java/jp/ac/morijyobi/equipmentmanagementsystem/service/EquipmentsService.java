package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;

public interface EquipmentsService {
    void insert(Equipment equipment);

    Equipment selectById(int id);
}
