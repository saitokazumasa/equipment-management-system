package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentCategoriesMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IGetEquipmentCategoryService;
import org.springframework.stereotype.Service;

@Service
public class GetEquipmentCategoryService implements IGetEquipmentCategoryService {
    private final IEquipmentCategoriesMapper equipmentCategoriesMapper;

    public GetEquipmentCategoryService(IEquipmentCategoriesMapper equipmentCategoriesMapper) {
        this.equipmentCategoriesMapper = equipmentCategoriesMapper;
    }

    @Override
    public EquipmentCategory executeById(final int id) {
        return equipmentCategoriesMapper.selectById(id);
    }
}
