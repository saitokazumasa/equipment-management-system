package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApproval;

public interface IGetCheckoutApprovalService {
    public CheckoutApproval executeByCheckoutApplicationId(final int id);
}
