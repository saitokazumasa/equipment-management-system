package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IGetCheckoutService;
import org.springframework.stereotype.Service;

@Service
public class GetCheckoutService implements IGetCheckoutService {
    private final ICheckoutApplicationsMapper checkoutApplicationsMapper;

    public GetCheckoutService(ICheckoutApplicationsMapper checkoutApplicationsMapper) {
        this.checkoutApplicationsMapper = checkoutApplicationsMapper;
    }

    @Override
    public CheckoutApplication execute(final int checkoutId) {
        return checkoutApplicationsMapper.selectById(checkoutId);
    }

}
