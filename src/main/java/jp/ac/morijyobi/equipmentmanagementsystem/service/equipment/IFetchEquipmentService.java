package jp.ac.morijyobi.equipmentmanagementsystem.service.equipment;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;

import java.util.List;

public interface IFetchEquipmentService {
    public Equipment executeById(final int id);

    public List<Equipment> executeByUserInput(final String name, final List<Integer> categoryIdList, final List<EquipmentState> stateList);
}
