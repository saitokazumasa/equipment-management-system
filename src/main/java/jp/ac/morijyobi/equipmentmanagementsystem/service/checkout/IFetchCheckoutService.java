package jp.ac.morijyobi.equipmentmanagementsystem.service.checkout;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;

public interface IFetchCheckoutService {
    public CheckoutApplication fetchNotReturned(final String mail, final int equipmentId);
}
