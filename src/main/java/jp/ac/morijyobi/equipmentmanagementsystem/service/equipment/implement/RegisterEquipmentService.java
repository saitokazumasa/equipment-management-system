package jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.IRegisterEquipmentService;
import org.springframework.stereotype.Service;

@Service
public class RegisterEquipmentService implements IRegisterEquipmentService {
    private final IEquipmentsMapper equipmentsMapper;

    public RegisterEquipmentService(final IEquipmentsMapper equipmentsMapper) {
        this.equipmentsMapper = equipmentsMapper;
    }

    @Override
    public int execute(final Equipment equipment) {
        return this.equipmentsMapper.insert(equipment);
    }
}
