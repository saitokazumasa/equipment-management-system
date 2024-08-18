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
        String a = name;
        List<Integer> b = categoryIdList;
        List<EquipmentState> c = stateList;

        if(b.isEmpty()) {
            List<EquipmentCategory> categories = this.equipmentCategoriesMapper.selectAll();

            for (EquipmentCategory category : categories) {
                b.add(category.getId());
            }
        }

        if (c.isEmpty()) {
            c = List.of(EquipmentState.values());
        }

        List<Equipment> test = this.equipmentsMapper.selectByUserInput(a, b, c);

        return test;
    }
}
