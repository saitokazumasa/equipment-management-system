package jp.ac.morijyobi.equipmentmanagementsystem.service.impl;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.ReturnApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.ReturnApplicationForm;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IReturnApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IReturnApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReturnApplicationService implements IReturnApplicationService {
    private final IReturnApplicationsMapper returnApplicationsMapper;
    private final IAccountsMapper accountsMapper;
    private final ICheckoutApplicationsMapper checkoutApplicationsMapper;

    public ReturnApplicationService(final IReturnApplicationsMapper returnApplicationsMapper, final IAccountsMapper accountsMapper, IAccountsMapper accountsMapper1, ICheckoutApplicationsMapper checkoutApplicationsMapper) {
        this.returnApplicationsMapper = returnApplicationsMapper;
        this.accountsMapper = accountsMapper1;
        this.checkoutApplicationsMapper = checkoutApplicationsMapper;
    }

    @Override
    @Transactional
    public void execute(ReturnApplicationForm returnApplicationForm) {
        final Account account = accountsMapper.selectByMail(returnApplicationForm.mail());

        System.out.println("ReturnApplicationService.execute");
        System.out.println(returnApplicationForm.json());
        System.out.println(returnApplicationForm.damageList());
        System.out.println("id:"+account.getId());

        for (final Equipment equipment : returnApplicationForm.equipments()) {
            final CheckoutApplication checkoutApplication = checkoutApplicationsMapper.selectNotReturned(account.getId(), equipment.getId());
            final var returnApplication = new ReturnApplication(
                    -1,
                    checkoutApplication.getId(),
                    LocalDateTime.now()
            );

            returnApplicationsMapper.insert(returnApplication);
        }

    }
}
