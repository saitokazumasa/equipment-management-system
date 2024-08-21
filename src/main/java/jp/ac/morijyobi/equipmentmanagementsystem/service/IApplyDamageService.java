package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.ReturnApplicationForm;

public interface IApplyDamageService {
    int execute(final ReturnApplicationForm returnApplicationForm);
}
