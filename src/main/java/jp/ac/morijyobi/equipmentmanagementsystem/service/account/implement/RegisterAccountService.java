package jp.ac.morijyobi.equipmentmanagementsystem.service.account.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.TemporaryAccount;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.TemporaryAccountRegistrationForm;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.account.IRegisterAccountService;
import org.springframework.transaction.annotation.Transactional;

public class RegisterAccountService implements IRegisterAccountService {
    private final IAccountsMapper accountsMapper;

    public RegisterAccountService(final IAccountsMapper accountsMapper) {
        this.accountsMapper = accountsMapper;
    }

    @Override
    @Transactional
    public int execute(final TemporaryAccountRegistrationForm temporaryAccountRegistrationForm) {
        for (final TemporaryAccount temporaryAccount : temporaryAccountRegistrationForm.temporaryAccounts()) {
            final Account account = temporaryAccount.toAccount();
            final int result = this.accountsMapper.insert(account);

            // 1 以外はエラー
            if (result != 1) return result;
        }

        // 1 は成功
        return 1;
    }
}
