package jp.ac.morijyobi.equipmentmanagementsystem.service.checkout.implement;


import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.CheckoutApplicationForm;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.checkout.IApplyCheckoutService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ApplyCheckoutService implements IApplyCheckoutService {
    private final ICheckoutApplicationsMapper checkoutApplicationsMapper;
    private final IAccountsMapper accountsMapper;

    public ApplyCheckoutService(
            final ICheckoutApplicationsMapper checkoutApplicationsMapper,
            final IAccountsMapper accountsMapper
    ) {
        this.checkoutApplicationsMapper = checkoutApplicationsMapper;
        this.accountsMapper = accountsMapper;
    }

    @Override
    @Transactional
    public int execute(final String mail, final CheckoutApplicationForm checkoutApplicationForm) {
        final Account account = accountsMapper.selectByMail(mail);

        for (final Equipment equipment : checkoutApplicationForm.equipments()) {
            final var checkoutApplication = new CheckoutApplication(
                    -1,
                    equipment.getId(),
                    account.getId(),
                    LocalDateTime.now()
            );

            final int result = checkoutApplicationsMapper.insert(checkoutApplication);

            if (result != 1) return result;
        }

        return 1;
    }
}