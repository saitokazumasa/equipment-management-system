package jp.ac.morijyobi.equipmentmanagementsystem.service.damage.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.DamagedApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.DamagedCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IDamagedApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.damage.IApplyDamageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApplyDamageService implements IApplyDamageService {
    private final IDamagedApplicationsMapper damagedApplicationsMapper;

    public ApplyDamageService(IDamagedApplicationsMapper damagedApplicationsMapper) {
        this.damagedApplicationsMapper = damagedApplicationsMapper;
    }

    @Override
    public int execute(String damageReason, int checkoutApplicationId, DamagedCategory damagedCategory) {
        final DamagedApplication damagedApplication = new DamagedApplication(
                -1,
                checkoutApplicationId,
                damageReason,
                damagedCategory,
                LocalDateTime.now()
        );

        return damagedApplicationsMapper.insert(damagedApplication);
    }
}
