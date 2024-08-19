package jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentCategoriesMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.IFetchEquipmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FetchEquipmentService implements IFetchEquipmentService {
    private final IEquipmentsMapper equipmentsMapper;
    private final IEquipmentCategoriesMapper equipmentCategoriesMapper;

    public FetchEquipmentService(final IEquipmentsMapper equipmentsMapper, IEquipmentCategoriesMapper equipmentCategoriesMapper) {
        this.equipmentsMapper = equipmentsMapper;
        this.equipmentCategoriesMapper = equipmentCategoriesMapper;
    }

    @Override
    public Equipment executeById(final int id) {
        return this.equipmentsMapper.selectById(id);
    }

    @Override
    public List<Equipment> executeByUserInput(final String name, final List<Integer> categoryIdList, final List<EquipmentState> stateList) {
        final boolean categoryIsEmpty = categoryIdList.isEmpty();
        final boolean stateIsEmpty = stateList.isEmpty();

        if(categoryIsEmpty) {
            List<EquipmentCategory> categories = this.equipmentCategoriesMapper.selectAll();
            for (EquipmentCategory category : categories) {
                categoryIdList.add(category.getId());
            }
        }
        if (stateIsEmpty) {
            stateList.add(EquipmentState.AVAILABLE_FOR_LOAN);
            stateList.add(EquipmentState.ON_LOAN);
            stateList.add(EquipmentState.NOT_AVAILABLE_FOR_LOAN);
        }

        List<Equipment> result = this.equipmentsMapper.selectByUserInput(name, categoryIdList, stateList);

        if (categoryIsEmpty) {
            categoryIdList.clear();
        }
        if (stateIsEmpty) {
            stateList.clear();
        }

        return result;
    }
}
