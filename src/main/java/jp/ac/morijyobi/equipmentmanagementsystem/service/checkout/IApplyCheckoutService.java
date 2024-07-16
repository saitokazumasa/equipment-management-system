package jp.ac.morijyobi.equipmentmanagementsystem.service.checkout;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.CheckoutApplicationForm;

public interface IApplyCheckoutService {
    public int execute(final String mail, final CheckoutApplicationForm checkoutApplicationForm);
}
