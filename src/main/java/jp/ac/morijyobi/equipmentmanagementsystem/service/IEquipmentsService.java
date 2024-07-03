package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;

import java.util.List;

public interface IEquipmentsService {
    void register(final Equipment equipment);

    Equipment fetchById(final int id);

    List<Equipment> fetchAll();
}
