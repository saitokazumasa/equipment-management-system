package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;

import java.util.List;

public interface IEquipmentService {
    public void register(final Equipment equipment);

    public Equipment fetchById(final int id);

    public List<Equipment> fetchAll();
}
