package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentCategoriesMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IRegisterEquipmentCategoryService;
import org.springframework.stereotype.Service;

@Service
public class RegisterEquipmentCategoryService implements IRegisterEquipmentCategoryService {
    private final IEquipmentCategoriesMapper equipmentCategoriesMapper;

    public RegisterEquipmentCategoryService(final IEquipmentCategoriesMapper equipmentCategoriesMapper) {
        this.equipmentCategoriesMapper = equipmentCategoriesMapper;
    }

    @Override
    public EquipmentCategory execute(final String name) {
        final EquipmentCategory value = this.equipmentCategoriesMapper.selectByName(name);
        if (value != null) return value;

        final var newValue = new EquipmentCategory(-1, name);
        // NOTE: MyBatis により newValue.id がオートインクリメント値に書き変わっている
        this.equipmentCategoriesMapper.insert(newValue);
        return newValue;
    }
}
