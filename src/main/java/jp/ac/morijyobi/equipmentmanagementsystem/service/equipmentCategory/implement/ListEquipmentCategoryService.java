package jp.ac.morijyobi.equipmentmanagementsystem.service.equipmentCategory.implement;


import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentCategoriesMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipmentCategory.IListEquipmentCategoryService;

import java.util.List;

public class ListEquipmentCategoryService implements IListEquipmentCategoryService {
    private final IEquipmentCategoriesMapper equipmentCategoriesMapper;

    public ListEquipmentCategoryService(IEquipmentCategoriesMapper equipmentCategoriesMapper) {
        this.equipmentCategoriesMapper = equipmentCategoriesMapper;
    }

    @Override
    public List<EquipmentCategory> execute() {
        return equipmentCategoriesMapper.selectAll();
    }
}
