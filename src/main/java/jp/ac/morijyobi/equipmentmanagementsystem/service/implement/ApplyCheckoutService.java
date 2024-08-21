package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;


import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.EquipmentList;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IApplyCheckoutService;
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
    public void execute(final String mail, final EquipmentList equipmentList) {
        final Account account = this.accountsMapper.selectByMail(mail);

        for (final Equipment equipment : equipmentList.getValues()) {
            final var value = new CheckoutApplication(
                    -1,
                    equipment.getId(),
                    account.getId(),
                    LocalDateTime.now()
            );
            this.checkoutApplicationsMapper.insert(value);
        }
    }
}