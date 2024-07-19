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
import java.util.List;

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
        final Account account = this.accountsMapper.selectByMail(mail);

        for (final Equipment equipment : checkoutApplicationForm.equipments()) {
            final var checkoutApplication = new CheckoutApplication(
                    -1,
                    equipment.getId(),
                    account.getId(),
                    LocalDateTime.now()
            );

            final int result = this.checkoutApplicationsMapper.insert(checkoutApplication);

            // 1 以外はエラー
            if (result != 1) return result;
        }

        // 1 は成功
        return 1;
    }

    @Override
    public CheckoutApplication fetchNotReturned(String mail, int equipmentId) {
        final Account account = accountsMapper.selectByMail(mail);
        return checkoutApplicationsMapper.selectNotReturned(account.getId(), equipmentId);
    }
}