package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;


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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ApplyCheckoutService implements IApplyCheckoutService {
    private final IAccountsMapper accountsMapper;
    private final ICheckoutApplicationsMapper checkoutApplicationsMapper;
    private final IApplyReturnService applyReturnService;

    public ApplyCheckoutService(
            final IAccountsMapper accountsMapper,
            final ICheckoutApplicationsMapper checkoutApplicationsMapper,
            final IApplyReturnService applyReturnService
    ) {
        this.accountsMapper = accountsMapper;
        this.checkoutApplicationsMapper = checkoutApplicationsMapper;
        this.applyReturnService = applyReturnService;
    }

    @Override
    @Transactional
    public void execute(final String mail, final CheckoutEquipmentList checkoutEquipmentList) throws Exception {
        final Account account = this.accountsMapper.selectByMail(mail);

        for (final Equipment equipment : checkoutEquipmentList.getValues()) {
            // 未返却処理の備品は同時に返却処理を行う
            if (isNotReturned(equipment.getId())) this.applyReturnService.execute(equipment.getId());

            final var value = new CheckoutApplication(
                    -1,
                    equipment.getId(),
                    account.getId(),
                    LocalDateTime.now()
            );
            this.checkoutApplicationsMapper.insert(value);

            // TODO: 貸出承認時に備品情報の貸出状況を更新する
//            final int result = this.equipmentsMapper.updateState(equipment.getId(), EquipmentState.ON_LOAN);
//            if (result != 1) throw new Exception("更新に失敗しました");
        }
    }

    private boolean isNotReturned(final int equipmentId) {
        return this.checkoutApplicationsMapper.selectNotReturnedByEquipmentId(equipmentId) != null;
    }
}