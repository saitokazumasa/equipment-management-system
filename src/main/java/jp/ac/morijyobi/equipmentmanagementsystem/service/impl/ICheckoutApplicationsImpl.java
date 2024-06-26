package jp.ac.morijyobi.equipmentmanagementsystem.service.impl;


import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.ICheckoutApplicationsService;
import org.springframework.stereotype.Service;

@Service
public class ICheckoutApplicationsImpl implements ICheckoutApplicationsService {
    private final ICheckoutApplicationsMapper checkoutApplicationsMapper;

    public ICheckoutApplicationsImpl(final ICheckoutApplicationsMapper checkoutApplicationsMapper) {
        this.checkoutApplicationsMapper = checkoutApplicationsMapper;
    }


    @Override
    public void insert(final CheckoutApplication checkoutApplication) {
        checkoutApplicationsMapper.insert(checkoutApplication);
    }
}