package jp.ac.morijyobi.equipmentmanagementsystem.service.checkout.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.checkout.IFetchCheckoutService;
import org.springframework.stereotype.Service;

@Service
public class FetchCheckoutService implements IFetchCheckoutService {
    private final ICheckoutApplicationsMapper checkoutApplicationsMapper;

    public FetchCheckoutService(ICheckoutApplicationsMapper checkoutApplicationsMapper) {
        this.checkoutApplicationsMapper = checkoutApplicationsMapper;
    }

    @Override
    public CheckoutApplication executeByEquipmentIdAndAccountId(final int equipmentId, final int accountId) {
        return checkoutApplicationsMapper.selectByEquipmentIdAndAccountId(equipmentId, accountId);
    }
}
