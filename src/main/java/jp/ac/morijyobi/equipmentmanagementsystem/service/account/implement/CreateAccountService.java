package jp.ac.morijyobi.equipmentmanagementsystem.service.account.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.TemporaryAccount;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.CreateTemporaryAccountForm;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.account.ICreateAccountService;
import org.springframework.transaction.annotation.Transactional;

public class CreateAccountService implements ICreateAccountService {
    private final IAccountsMapper accountsMapper;

    public CreateAccountService(final IAccountsMapper accountsMapper) {
        this.accountsMapper = accountsMapper;
    }

    @Override
    @Transactional
    public int execute(final CreateTemporaryAccountForm createTemporaryAccountForm) {
        for (final TemporaryAccount temporaryAccount : createTemporaryAccountForm.temporaryAccounts()) {
            final Account account = temporaryAccount.toAccount();
            final int result = this.accountsMapper.insert(account);

            // 1 以外はエラー
            if (result != 1) return result;
        }

        // 1 は成功
        return 1;
    }
}
