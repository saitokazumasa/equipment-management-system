package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;

public interface IApproveCheckoutService {
    public void execute(final String mail, final CheckoutApplication checkoutApplication ) throws Exception;
}
