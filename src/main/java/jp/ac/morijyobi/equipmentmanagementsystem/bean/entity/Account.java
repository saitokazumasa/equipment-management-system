package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import jp.ac.morijyobi.equipmentmanagementsystem.constant.AccountCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    public int id;
    public String name;
    public String mail;
    public String password;
    public AccountCategory category;
    public Boolean isEnable;

    public Account cryptPassword() {
        final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        final String newPassword = passwordEncoder.encode(this.password);

        return new Account(
                this.id,
                this.name,
                this.mail,
                newPassword,
                this.category,
                this.isEnable
        );
    }
}
