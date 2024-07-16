package jp.ac.morijyobi.equipmentmanagementsystem.service.account;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.CreateTemporaryAccountForm;

public interface ICreateAccountService {
    public int execute(final CreateTemporaryAccountForm createTemporaryAccountForm);
}
