package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.EquipmentList;

public interface IApplyCheckoutService {
    public void execute(final String mail, final EquipmentList equipmentList);
}
