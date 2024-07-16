package jp.ac.morijyobi.equipmentmanagementsystem.service.impl;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.*;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.ReturnApplicationForm;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.DamagedCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IDamagedApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IDamageApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DamageApplicationService implements IDamageApplicationService {
    private final IDamagedApplicationsMapper damagedApplicationMapper;
    private final IAccountsMapper accountsMapper;
    private final ICheckoutApplicationsMapper checkoutApplicationsMapper;

    public DamageApplicationService(IDamagedApplicationsMapper damagedApplicationMapper, IAccountsMapper accountsMapper, ICheckoutApplicationsMapper checkoutApplicationsMapper) {
        this.damagedApplicationMapper = damagedApplicationMapper;
        this.accountsMapper = accountsMapper;
        this.checkoutApplicationsMapper = checkoutApplicationsMapper;
    }

    @Override
    @Transactional
    public int execute(ReturnApplicationForm returnApplicationForm) {
        if (!returnApplicationForm.damageList().isEmpty()) {
            final Account account = accountsMapper.selectByMail(returnApplicationForm.mail());

            for (final Damage damage : returnApplicationForm.damages()) {
                final CheckoutApplication checkoutApplication = checkoutApplicationsMapper.selectNotReturned(account.getId(), damage.getId());
                final var damagedApplication = new DamagedApplication(
                        -1,
                        checkoutApplication.getId(),
                        damage.getReason(),
                        DamagedCategory.DAMAGED,
                        LocalDateTime.now()
                );
                final int result = damagedApplicationMapper.insert(damagedApplication);

                if (result != 1) return result;
            }
        }
        return 1;
    }
}
