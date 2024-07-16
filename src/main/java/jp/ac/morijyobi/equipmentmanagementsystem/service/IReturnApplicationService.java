package jp.ac.morijyobi.equipmentmanagementsystem.service;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.ReturnApplicationForm;

public interface IReturnApplicationService {
    int execute(final ReturnApplicationForm returnApplicationForm);
}
