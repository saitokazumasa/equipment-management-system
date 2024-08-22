package jp.ac.morijyobi.equipmentmanagementsystem.service.checkout;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;

public interface IFetchCheckoutService {
    public CheckoutApplication executeByEquipmentIdAndAccountId(final int equipmentId, final int accountId);
}
