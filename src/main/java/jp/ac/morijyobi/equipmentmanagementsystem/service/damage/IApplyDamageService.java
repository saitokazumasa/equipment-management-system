package jp.ac.morijyobi.equipmentmanagementsystem.service.damage;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.ReturnApplicationForm;

public interface IApplyDamageService {
    int execute(final ReturnApplicationForm returnApplicationForm);
}
