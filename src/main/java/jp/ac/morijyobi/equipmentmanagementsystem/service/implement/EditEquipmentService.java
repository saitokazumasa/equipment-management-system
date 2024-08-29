package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.EditEquipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEditLogsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IEditEquipmentService;
import org.springframework.stereotype.Service;

@Service
public class EditEquipmentService implements IEditEquipmentService {
    private final IEquipmentsMapper equipmentsMapper;
    private final IEditLogsMapper editLogsMapper;

    public EditEquipmentService(IEquipmentsMapper equipmentsMapper, IEditLogsMapper editLogsMapper) {
        this.equipmentsMapper = equipmentsMapper;
        this.editLogsMapper = editLogsMapper;
    }

    @Override
    public void execute(final EditEquipment editEquipment) {
    }
}
