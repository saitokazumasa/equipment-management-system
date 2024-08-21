package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.ReturnEquipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.ReturnEquipmentList;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.ReturnApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IReturnApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IApplyDamagedService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IApplyReturnService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ApplyReturnService implements IApplyReturnService {
    private final ICheckoutApplicationsMapper checkoutApplicationsMapper;
    private final IReturnApplicationsMapper returnApplicationsMapper;
    private final IApplyDamagedService applyDamagedService;

    public ApplyReturnService(
            final IReturnApplicationsMapper returnApplicationsMapper,
            final ICheckoutApplicationsMapper checkoutApplicationsMapper,
            final IApplyDamagedService applyDamagedService
    ) {
        this.returnApplicationsMapper = returnApplicationsMapper;
        this.checkoutApplicationsMapper = checkoutApplicationsMapper;
        this.applyDamagedService = applyDamagedService;
    }

    @Override
    @Transactional
    public void execute(final ReturnEquipmentList returnEquipmentList) throws Exception {
        for (final ReturnEquipment returnEquipment : returnEquipmentList.getValues()) {
            final int equipmentId = returnEquipment.getEquipment().getId();
            final CheckoutApplication checkoutApplication = this.checkoutApplicationsMapper
                    .selectNotReturnedByEquipmentId(equipmentId);

            final var value = new ReturnApplication(
                    -1,
                    checkoutApplication.getId(),
                    LocalDateTime.now()
            );
            this.returnApplicationsMapper.insert(value);

            // TODO: 返却申請承認時に更新する
//            final int result = this.equipmentsMapper.updateState(equipmentId, EquipmentState.AVAILABLE_FOR_LOAN);
//            if (result != 1) throw new Exception("更新に失敗しました");

            if (!returnEquipment.isHasDamaged()) return;

            this.applyDamagedService.execute(returnEquipment.getDamagedReason(), checkoutApplication.getId());
        }
    }
}
