package jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.IListEquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListEquipmentService implements IListEquipmentService {
    private final IEquipmentsMapper equipmentsMapper;

    public ListEquipmentService(final IEquipmentsMapper equipmentsMapper) {
        this.equipmentsMapper = equipmentsMapper;
    }

    @Override
    public List<Equipment> execute() {
        return equipmentsMapper.selectAll();
    }
}
