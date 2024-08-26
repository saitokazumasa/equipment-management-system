package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;


import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IListCheckoutService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCheckoutService implements IListCheckoutService {
    private final ICheckoutApplicationsMapper checkoutApplicationsMapper;

    public ListCheckoutService(ICheckoutApplicationsMapper checkoutApplicationsMapper) {
        this.checkoutApplicationsMapper = checkoutApplicationsMapper;
    }

    @Override
    public List<CheckoutApplication> execute(final List<Integer> checkoutId) {
        return checkoutApplicationsMapper.selectByIds(checkoutId);
    }
}
