package jp.ac.morijyobi.equipmentmanagementsystem.service.impl;


import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.CheckoutApplicationsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutApplicationsImpl implements CheckoutApplicationsService{
    private final ICheckoutApplicationsMapper checkoutApplicationsMapper;

    public CheckoutApplicationsImpl(ICheckoutApplicationsMapper checkoutApplicationsMapper) {
        this.checkoutApplicationsMapper = checkoutApplicationsMapper;
    }


    @Override
    public void insert(CheckoutApplication checkoutApplication) {
        checkoutApplicationsMapper.insert(checkoutApplication);
    }
}