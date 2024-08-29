package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IUpdateEquipmentService;
import org.springframework.stereotype.Service;

@Service
public class UpdateEquipmentService implements IUpdateEquipmentService {
    private final IEquipmentsMapper equipmentsMapper;

    public UpdateEquipmentService(final IEquipmentsMapper equipmentsMapper) {
        this.equipmentsMapper = equipmentsMapper;
    }

    @Override
    public int executeToState(final int equipmentId, final EquipmentState state) {
        return this.equipmentsMapper.updateState(equipmentId, state);
    }
}
