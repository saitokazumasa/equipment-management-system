package jp.ac.morijyobi.equipmentmanagementsystem.service.damage;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.DamagedApplication;

public interface IApplyDamageService {
    public int execute(final DamagedApplication DamagedApplication);
}