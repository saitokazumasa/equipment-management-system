package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;

public interface EquipmentsService {
    void insert(final Equipment equipment);

    Equipment selectById(final int id);
}
