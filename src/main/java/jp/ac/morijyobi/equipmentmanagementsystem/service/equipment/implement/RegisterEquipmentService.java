package jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.equipmentRegisterForm;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
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
    public int execute(equipmentRegisterForm equipmentRegisterForm) {
        final Equipment equipment = new Equipment();

        equipment.setName(equipmentRegisterForm.getEquipmentName());
        equipment.setCategoryId(Integer.parseInt(equipmentRegisterForm.getCategory()));
        equipment.setStorageLocationId(1);
        equipment.setState(EquipmentState.AVAILABLE_FOR_LOAN);

        if (equipmentRegisterForm.getIsPeriod()) {
            equipment.setLendingPeriod(equipmentRegisterForm.getLendingPeriod());
            equipment.setNotificationDate(equipmentRegisterForm.getNotificationDate());
        } else {
            equipment.setLendingPeriod(-1);
            equipment.setNotificationDate(-1);
        }

        equipment.setRemark(equipmentRegisterForm.getRemark());

        return this.equipmentsMapper.insert(equipment);
    }
}
