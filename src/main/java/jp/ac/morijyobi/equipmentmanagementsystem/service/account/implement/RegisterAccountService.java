package jp.ac.morijyobi.equipmentmanagementsystem.service.account.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterAccountList;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterAccount;
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
    public boolean execute(final RegisterAccountList registerAccountList) {
        for (final RegisterAccount registerAccount : registerAccountList.getValues()) {
            final Account account = registerAccount.toAccount();
            final Account newAccount = account.cryptPassword();
            final int result = this.accountsMapper.insert(newAccount);

            if (result != 1) return false;
        }
        return true;
    }
}
