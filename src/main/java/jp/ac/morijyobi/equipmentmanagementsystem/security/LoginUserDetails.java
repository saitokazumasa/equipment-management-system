package jp.ac.morijyobi.equipmentmanagementsystem.security;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LoginUserDetails implements UserDetails {

    private final Account account;

    public LoginUserDetails(final Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_" + account.category);
    }

    @Override
    public String getPassword() {
        return account.password;
    }

    /**
     * メールアドレスを "username" として扱う
     * @return メールアドレス
     */
    @Override
    public String getUsername() {
        return account.mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
