package jp.ac.morijyobi.equipmentmanagementsystem.bean.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.TemporaryAccount;
import lombok.Getter;

public class CreateTemporaryAccountForm {
    @Getter
    @NotBlank
    @Size(min = 1)
    private final String json;

    private final JsonUtil<TemporaryAccount[]> jsonUtil;

    public CreateTemporaryAccountForm(final String json) {
        this.json = json;
        this.jsonUtil = new JsonUtil<>(TemporaryAccount[].class);
    }

    public static CreateTemporaryAccountForm empty() {
        return new CreateTemporaryAccountForm("");
    }

    public TemporaryAccount[] temporaryAccounts() {
        final TemporaryAccount[] temporaryAccounts = this.jsonUtil.tryToObject(json);

        if (temporaryAccounts.length == 0) throw new ArrayIndexOutOfBoundsException();

        return temporaryAccounts;
    }
}
