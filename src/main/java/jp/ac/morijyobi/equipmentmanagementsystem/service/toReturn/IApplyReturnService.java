package jp.ac.morijyobi.equipmentmanagementsystem.service.toReturn;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.ReturnApplicationForm;

public interface IApplyReturnService {
    int execute(final ReturnApplicationForm returnApplicationForm);
}
