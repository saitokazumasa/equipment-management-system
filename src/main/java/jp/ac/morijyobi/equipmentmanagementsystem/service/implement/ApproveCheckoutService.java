package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApproval;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApprovalsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IApproveCheckoutService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IGetAccountService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IGetEquipmentService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IUpdateEquipmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ApproveCheckoutService implements IApproveCheckoutService {
    private final IGetAccountService getAccountService;
    private final IGetEquipmentService getEquipmentService;
    private final ICheckoutApprovalsMapper checkoutApprovalsMapper;
    private final IUpdateEquipmentService updateEquipmentService;

    public ApproveCheckoutService(
            final IGetAccountService getAccountService,
            final IGetEquipmentService getEquipmentService,
            final ICheckoutApprovalsMapper checkoutApprovalsMapper,
            final IUpdateEquipmentService updateEquipmentService
    ) {
        this.getAccountService = getAccountService;
        this.getEquipmentService = getEquipmentService;
        this.checkoutApprovalsMapper = checkoutApprovalsMapper;
        this.updateEquipmentService = updateEquipmentService;
    }

    @Override
    @Transactional
    public void execute(final String mail, final CheckoutApplication checkoutApplication) throws Exception{
        final int accountId = getAccountService.executeByMail(mail).getId();
        final Equipment equipment = this.getEquipmentService.executeAvailableForLoanById(checkoutApplication.getEquipmentId());

        final CheckoutApproval checkoutApproval = new CheckoutApproval (
                -1,
                checkoutApplication.getId(),
                accountId,
                LocalDateTime.now()
        );
        checkoutApprovalsMapper.insert(checkoutApproval);

        final int result = this.updateEquipmentService.executeToState(equipment.getId(), EquipmentState.ON_LOAN);
        if (result != 1) throw new Exception("更新に失敗しました");
    }
}
