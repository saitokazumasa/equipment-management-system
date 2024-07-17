package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.CheckoutApplicationForm;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;

public interface ICheckoutApplicationService {
    int execute(final CheckoutApplicationForm checkoutApplicationForm);

    CheckoutApplication fetchNotReturned(final String mail, final int equipmentId);
}
