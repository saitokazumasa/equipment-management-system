package jp.ac.morijyobi.equipmentmanagementsystem.service.damage;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.DamagedApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.DamagedCategory;

public interface IApplyDamageService {
    public int execute(final String damageReason, final int checkoutApplicationId, final DamagedCategory damagedCategory);
}