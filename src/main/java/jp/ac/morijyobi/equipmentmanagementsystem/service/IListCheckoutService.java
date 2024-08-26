package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;

import java.util.List;

public interface IListCheckoutService {
    public List<CheckoutApplication> execute(final List<Integer> checkoutId);
}
