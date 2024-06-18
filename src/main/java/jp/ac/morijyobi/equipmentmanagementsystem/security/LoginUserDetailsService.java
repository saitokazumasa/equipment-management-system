package jp.ac.morijyobi.equipmentmanagementsystem.security;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailsService implements UserDetailsService {

    private final IAccountsMapper accountsMapper;

    public LoginUserDetailsService(final IAccountsMapper accountsMapper) {
        this.accountsMapper = accountsMapper;
    }

    /**
     * メールアドレスを "username" として扱う
     * @param mail メールアドレス
     */
    @Override
    public UserDetails loadUserByUsername(final String mail) throws UsernameNotFoundException {
        final Account account = accountsMapper.selectByMail(mail);

        if (account == null) throw new UsernameNotFoundException(mail);

        return new LoginUserDetails(account);
    }
}
