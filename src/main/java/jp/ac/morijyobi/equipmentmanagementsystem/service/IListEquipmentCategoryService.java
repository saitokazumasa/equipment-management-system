package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;

import java.util.List;

public interface IListEquipmentCategoryService {
    public List<EquipmentCategory> execute();
    public List<Integer> ids();
}
