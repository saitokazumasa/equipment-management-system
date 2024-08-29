package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;


import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.CheckoutEquipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.CheckoutEquipmentList;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IApplyCheckoutService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IApplyReturnService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IGetAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ApplyCheckoutService implements IApplyCheckoutService {
    private final IGetAccountService getAccountService;
    private final ICheckoutApplicationsMapper checkoutApplicationsMapper;
    private final IApplyReturnService applyReturnService;

    public ApplyCheckoutService(
            final IGetAccountService getAccountService,
            final ICheckoutApplicationsMapper checkoutApplicationsMapper,
            final IApplyReturnService applyReturnService
    ) {
        this.getAccountService = getAccountService;
        this.checkoutApplicationsMapper = checkoutApplicationsMapper;
        this.applyReturnService = applyReturnService;
    }

    @Override
    @Transactional
    public void execute(final String mail, final CheckoutEquipmentList checkoutEquipmentList) throws Exception {
        final Account account = this.getAccountService.executeByMail(mail);

        for (final CheckoutEquipment checkoutEquipment : checkoutEquipmentList.getValues()) {
            // 未返却処理の備品は同時に返却処理を行う
            final int equipmentId = checkoutEquipment.getEquipment().getId();
            if (isNotReturned(equipmentId)) this.applyReturnService.execute(equipmentId);

            final var value = new CheckoutApplication(
                    -1,
                    equipmentId,
                    account.getId(),
                    LocalDateTime.now()
            );
            this.checkoutApplicationsMapper.insert(value);
        }
    }

    private boolean isNotReturned(final int equipmentId) {
        return this.checkoutApplicationsMapper.selectNotReturnedByEquipmentId(equipmentId) != null;
    }
}