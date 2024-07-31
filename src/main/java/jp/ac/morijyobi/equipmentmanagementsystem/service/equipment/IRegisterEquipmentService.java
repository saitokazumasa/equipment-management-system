package jp.ac.morijyobi.equipmentmanagementsystem.service.equipment;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.equipmentRegisterForm;

public interface IRegisterEquipmentService {
    public int execute(final equipmentRegisterForm equipmentRegisterForm);
}
