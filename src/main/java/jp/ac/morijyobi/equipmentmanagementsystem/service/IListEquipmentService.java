package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.EquipmentSearchCriteria;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;

import java.util.List;

public interface IListEquipmentService {
    public List<Equipment> execute();
    public List<Equipment> search(final EquipmentSearchCriteria equipmentSearchCriteria);
    public List<Equipment> searchByIds(final List<Integer> equipmentIds);
}
