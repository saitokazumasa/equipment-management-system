package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import jp.ac.morijyobi.equipmentmanagementsystem.constant.AccountCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemporaryAccount {
    public String name;
    public String mail;
    public AccountCategory category;

    public Account toAccount() {
        return new Account(
                -1,
                this.name,
                this.mail,
                // 仮パスワード
                "morijyobi",
                this.category,
                true
        );
    }
}
