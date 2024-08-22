package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.CheckoutEquipmentList;

public interface IApplyCheckoutService {
    public void execute(final String mail, final CheckoutEquipmentList checkoutEquipmentList) throws Exception;
}
