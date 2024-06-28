package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;

import java.util.List;

public interface IEquipmentsService {
    void insert(final Equipment equipment);

    Equipment selectById(final int id);

    List<Equipment> selectAll();
}
