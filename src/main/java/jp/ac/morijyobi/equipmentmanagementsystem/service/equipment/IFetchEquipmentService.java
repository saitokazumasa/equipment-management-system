package jp.ac.morijyobi.equipmentmanagementsystem.service.equipment;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;

import java.util.List;

public interface IFetchEquipmentService {
    public Equipment executeById(final int id);

    public List<Equipment> executeAll();

    public List<Equipment> executeLending(final String mail);
}
