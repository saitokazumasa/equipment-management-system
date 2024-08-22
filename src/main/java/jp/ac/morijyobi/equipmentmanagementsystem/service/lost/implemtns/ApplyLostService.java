package jp.ac.morijyobi.equipmentmanagementsystem.service.lost.implemtns;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.DamagedApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.DamagedCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IDamagedApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.lost.IApplyLostService;
import org.springframework.stereotype.Service;

@Service
public class ApplyLostService implements IApplyLostService {
    private final IDamagedApplicationsMapper damagedApplicationsMapper;

    public ApplyLostService(IDamagedApplicationsMapper damagedApplicationsMapper) {
        this.damagedApplicationsMapper = damagedApplicationsMapper;
    }


    @Override
    public int execute(final String damageReason, final int checkoutApplicationId) {
        final DamagedApplication damagedApplication = new DamagedApplication(
                -1,
                checkoutApplicationId,
                damageReason,
                DamagedCategory.LOST,
                null
        );

        return damagedApplicationsMapper.insert(damagedApplication);
    }
}
