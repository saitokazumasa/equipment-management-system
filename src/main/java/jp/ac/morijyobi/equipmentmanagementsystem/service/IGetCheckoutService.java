package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;

public interface IGetCheckoutService {
    public CheckoutApplication execute(final int checkoutId);
}
