package jp.ac.morijyobi.equipmentmanagementsystem.bean.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class RegisterAccountList implements Serializable {
    @NotNull
    @NotEmpty
    @Valid
    private List<RegisterAccount> values;

    public static RegisterAccountList empty() {
        final var list = new ArrayList<RegisterAccount>();
        return new RegisterAccountList(list);
    }

    public boolean isNull() {
        return this.values == null;
    }

    public RegisterAccountList add(final RegisterAccount value) {
        final var list = new ArrayList<>(values);
        list.add(value);
        return new RegisterAccountList(list);
    }

    public RegisterAccountList remove(final int index) {
        final var list = new ArrayList<>(values);
        list.remove(index);
        return new RegisterAccountList(list);
    }
}
