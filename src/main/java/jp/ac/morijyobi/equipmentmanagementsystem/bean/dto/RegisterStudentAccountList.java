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
public class RegisterStudentAccountList implements Serializable {
    @NotNull
    @NotEmpty
    @Valid
    private List<RegisterStudentAccount> values;

    public static RegisterStudentAccountList empty() {
        final var list = new ArrayList<RegisterStudentAccount>();
        return new RegisterStudentAccountList(list);
    }

    public boolean isNull() {
        return this.values == null;
    }

    public RegisterStudentAccountList add(final RegisterStudentAccount value) {
        final var list = new ArrayList<>(this.values);
        list.add(value);
        return new RegisterStudentAccountList(list);
    }

    public RegisterStudentAccountList remove(final int index) {
        final var list = new ArrayList<>(this.values);
        list.remove(index);
        return new RegisterStudentAccountList(list);
    }
}
