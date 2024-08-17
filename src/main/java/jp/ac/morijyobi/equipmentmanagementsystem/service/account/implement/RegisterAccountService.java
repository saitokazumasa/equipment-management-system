package jp.ac.morijyobi.equipmentmanagementsystem.service.account.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterAccountList;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterAccount;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.account.IRegisterAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterAccountService implements IRegisterAccountService {
    private final IAccountsMapper accountsMapper;
    private final PasswordEncoder passwordEncoder;

    public RegisterAccountService(
            final IAccountsMapper accountsMapper,
            final PasswordEncoder passwordEncoder
    ) {
        this.accountsMapper = accountsMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public boolean execute(final RegisterAccountList registerAccountList) {
        for (final RegisterAccount registerAccount : registerAccountList.getValues()) {
            final Account account = registerAccount.toAccount();
            final Account newAccount = cryptPassword(account);
            final int result = this.accountsMapper.insert(newAccount);

            if (result != 1) return false;
        }
        return true;
    }

    private Account cryptPassword(final Account account) {
        final String password = passwordEncoder.encode(account.getPassword());

        return new Account(
                account.id,
                account.name,
                account.mail,
                password,
                account.category,
                account.isEnable
        );
    }
}
