package jp.ac.morijyobi.equipmentmanagementsystem.service.equipmentCategory.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentCategoriesMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipmentCategory.IListEquipmentCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListEquipmentCategoryService implements IListEquipmentCategoryService {
    private final IEquipmentCategoriesMapper equipmentCategoriesMapper;

    public ListEquipmentCategoryService(final IEquipmentCategoriesMapper equipmentCategoriesMapper) {
        this.equipmentCategoriesMapper = equipmentCategoriesMapper;
    }

    @Override
    public List<EquipmentCategory> execute() {
        return equipmentCategoriesMapper.selectAll();
    }

    @Override
    public List<Integer> ids() {
        final List<EquipmentCategory> equipmentCategoryList = execute();
        return equipmentCategoryList.stream()
                .map(EquipmentCategory::getId)
                .toList();
    }
}
