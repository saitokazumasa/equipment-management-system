package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.CheckoutApplicationForm;

public interface ICheckoutApplicationService {
    public int execute(final CheckoutApplicationForm checkoutApplicationForm);
}
