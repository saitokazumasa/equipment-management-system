package jp.ac.morijyobi.equipmentmanagementsystem.bean.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.AccountCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterAccount implements Serializable{
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String mail;

    private AccountCategory category;

    public static RegisterAccount empty() {
        return new RegisterAccount();
    }

    public Account toAccount() {
        return new Account(
                -1,
                this.name,
                this.mail,
                // NOTE: 仮パスワード
                "morijyobi",
                this.category,
                true
        );
    }
}
