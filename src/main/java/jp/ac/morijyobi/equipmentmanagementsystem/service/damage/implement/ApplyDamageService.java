package jp.ac.morijyobi.equipmentmanagementsystem.service.damage.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.DamagedApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IDamagedApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.damage.IApplyDamageService;
import org.springframework.stereotype.Service;

@Service
public class ApplyDamageService implements IApplyDamageService {
    private final IDamagedApplicationsMapper damagedApplicationsMapper;

    public ApplyDamageService(IDamagedApplicationsMapper damagedApplicationsMapper) {
        this.damagedApplicationsMapper = damagedApplicationsMapper;
    }

    @Override
    public int execute(final DamagedApplication DamagedApplication) {
        final int result = this.damagedApplicationsMapper.insert(DamagedApplication);

        return result;
    }
}
