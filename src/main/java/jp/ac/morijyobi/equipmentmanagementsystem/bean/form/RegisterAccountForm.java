package jp.ac.morijyobi.equipmentmanagementsystem.bean.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.util.JsonUtil;

import java.util.Arrays;
import java.util.List;

public record RegisterAccountForm(
        @NotBlank
        @Size(min = 1)
        String json
) {
    public static RegisterAccountForm empty() {
        return new RegisterAccountForm("");
    }

    public List<Account> accounts() {
        final var accounts = JsonUtil.tryToObject(json, Account[].class);

        if (accounts.length == 0) throw new ArrayIndexOutOfBoundsException();

        return Arrays.asList(accounts);
    }
}
