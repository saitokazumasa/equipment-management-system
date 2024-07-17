package jp.ac.morijyobi.equipmentmanagementsystem.service.account;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.TemporaryAccountRegistrationForm;

public interface IRegisterAccountService {
    public int execute(final TemporaryAccountRegistrationForm temporaryAccountRegistrationForm);
}
