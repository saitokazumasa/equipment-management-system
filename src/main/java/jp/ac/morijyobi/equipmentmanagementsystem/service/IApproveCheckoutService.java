package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.CheckoutIdList;

import java.util.List;

public interface IApproveCheckoutService {
    public void execute(final CheckoutIdList checkoutIdList, final String mail);
}
