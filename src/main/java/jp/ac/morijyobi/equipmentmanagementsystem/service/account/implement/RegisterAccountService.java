package jp.ac.morijyobi.equipmentmanagementsystem.service.account.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.RegisterAccountForm;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.account.IRegisterAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterAccountService implements IRegisterAccountService {
    private final IAccountsMapper accountsMapper;

    public RegisterAccountService(final IAccountsMapper accountsMapper) {
        this.accountsMapper = accountsMapper;
    }

    @Override
    @Transactional
    public int execute(final RegisterAccountForm registerAccountForm) {
        for (final Account account : registerAccountForm.accounts()) {
            final int result = this.accountsMapper.insert(account);
            // 1 以外はエラー
            if (result != 1) return result;
        }
        // 1 は成功
        return 1;
    }
}
