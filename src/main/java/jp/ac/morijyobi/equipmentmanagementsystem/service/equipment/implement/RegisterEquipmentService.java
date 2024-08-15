package jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.equipmentRegisterForm;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentCategoriesMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.IRegisterEquipmentService;
import org.springframework.stereotype.Service;

@Service
public class RegisterEquipmentService implements IRegisterEquipmentService {
    private final IEquipmentsMapper equipmentsMapper;
    private final IEquipmentCategoriesMapper equipmentCategoriesMapper;

    public RegisterEquipmentService(final IEquipmentsMapper equipmentsMapper, IEquipmentCategoriesMapper equipmentCategoriesMapper) {
        this.equipmentsMapper = equipmentsMapper;
        this.equipmentCategoriesMapper = equipmentCategoriesMapper;
    }


    @Override
    public int execute(equipmentRegisterForm equipmentRegisterForm) {
        final Equipment equipment = new Equipment();
        final EquipmentCategory equipmentCategory = new EquipmentCategory();


        final EquipmentCategory a = equipmentCategoriesMapper.selectByName(equipmentRegisterForm.getCategory());

        // aの結果がnullの場合、新規登録
        if (a == null) {
            equipmentCategory.setName(equipmentRegisterForm.getCategory());
            equipmentCategoriesMapper.insert(equipmentCategory);
            equipment.setCategoryId(equipmentCategoriesMapper.selectByName(equipmentRegisterForm.getCategory()).getId());
        } else {
            equipment.setCategoryId(a.getId());
        }

        equipment.setName(equipmentRegisterForm.getEquipmentName());
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
