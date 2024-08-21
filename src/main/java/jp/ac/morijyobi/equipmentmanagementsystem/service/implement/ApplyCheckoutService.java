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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
public class ApplyCheckoutService implements IApplyCheckoutService {
    private final IAccountsMapper accountsMapper;
    private final ICheckoutApplicationsMapper checkoutApplicationsMapper;
    private final IEquipmentsMapper equipmentsMapper;

    public ApplyCheckoutService(
            final IAccountsMapper accountsMapper,
            final ICheckoutApplicationsMapper checkoutApplicationsMapper,
            final IEquipmentsMapper equipmentsMapper
    ) {
        this.accountsMapper = accountsMapper;
        this.checkoutApplicationsMapper = checkoutApplicationsMapper;
        this.equipmentsMapper = equipmentsMapper;
    }

    @Override
    @Transactional
    public void execute(final String mail, final CheckoutEquipmentList checkoutEquipmentList) throws Exception {
        final Account account = this.accountsMapper.selectByMail(mail);

        for (final Equipment equipment : checkoutEquipmentList.getValues()) {
            // TODO: 既に借りられている状態のときは、返却処理を通す

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
}