package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CheckoutApplicationsService {

    public void insert(CheckoutApplication checkoutApplication);
}
