package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.DamagedApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.DamagedCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IDamagedApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IApplyDamagedService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApplyDamagedService implements IApplyDamagedService {
    private final IDamagedApplicationsMapper damagedApplicationMapper;

    public ApplyDamagedService(final IDamagedApplicationsMapper damagedApplicationMapper) {
        this.damagedApplicationMapper = damagedApplicationMapper;
    }

    @Override
    public void execute(final String damagedReason, final int checkoutApplicationId) {
        final var damagedApplication = new DamagedApplication(
                -1,
                checkoutApplicationId,
                damagedReason,
                DamagedCategory.DAMAGED,
                LocalDateTime.now()
        );
        this.damagedApplicationMapper.insert(damagedApplication);
    }
}
