package jp.ac.morijyobi.equipmentmanagementsystem.service.checkout.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.service.checkout.IFetchCheckoutService;
import org.springframework.stereotype.Service;

@Service
public class FetchCheckoutService implements IFetchCheckoutService{
    @Override
    public CheckoutApplication fetchNotReturned(String mail, int equipmentId) {
        return null;
    }
}