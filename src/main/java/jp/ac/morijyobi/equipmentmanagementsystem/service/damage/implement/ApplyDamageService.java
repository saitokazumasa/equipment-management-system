package jp.ac.morijyobi.equipmentmanagementsystem.service.damage.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.*;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.ReturnApplicationForm;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.DamagedCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IDamagedApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.damage.IApplyDamageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ApplyDamageService implements IApplyDamageService {
    private final IDamagedApplicationsMapper damagedApplicationMapper;
    private final ICheckoutApplicationsMapper checkoutApplicationsMapper;

    public ApplyDamageService(
            IDamagedApplicationsMapper damagedApplicationMapper,
            ICheckoutApplicationsMapper checkoutApplicationsMapper) {
        this.damagedApplicationMapper = damagedApplicationMapper;
        this.checkoutApplicationsMapper = checkoutApplicationsMapper;
    }

    @Override
    @Transactional
    public int execute(ReturnApplicationForm returnApplicationForm) {
        if(returnApplicationForm.damageList().isEmpty()) return 1;

        for (final Damage damage : returnApplicationForm.damages()) {
            final int accountId = checkoutApplicationsMapper.selectByEquipmentId(damage.getId());
            final CheckoutApplication checkoutApplication = checkoutApplicationsMapper.selectNotReturned(accountId,damage.getId());
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

        return 1;
    }
}
