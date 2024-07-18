package jp.ac.morijyobi.equipmentmanagementsystem.bean.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.TemporaryAccount;
import jp.ac.morijyobi.equipmentmanagementsystem.util.JsonUtil;
import lombok.Getter;

public class RegisterTemporaryAccountForm {
    @Getter
    @NotBlank
    @Size(min = 1)
    private final String json;

    private final JsonUtil<TemporaryAccount[]> jsonUtil;

    public RegisterTemporaryAccountForm(final String json) {
        this.json = json;
        this.jsonUtil = new JsonUtil<>(TemporaryAccount[].class);
    }

    public static RegisterTemporaryAccountForm empty() {
        return new RegisterTemporaryAccountForm("");
    }

    public TemporaryAccount[] temporaryAccounts() {
        final TemporaryAccount[] temporaryAccounts = this.jsonUtil.tryToObject(json);

        if (temporaryAccounts.length == 0) throw new ArrayIndexOutOfBoundsException();

        return temporaryAccounts;
    }
}
