package jp.ac.morijyobi.equipmentmanagementsystem.service.account;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.RegisterAccountForm;

public interface IRegisterAccountService {
    public int execute(final RegisterAccountForm registerAccountForm);
}
