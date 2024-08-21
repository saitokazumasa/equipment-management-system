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
public class RegisterEquipmentList implements Serializable {
    @NotNull
    @NotEmpty
    @Valid
    private List<RegisterEquipment> values;

    public static RegisterEquipmentList empty() {
        final var list = new ArrayList<RegisterEquipment>();
        return new RegisterEquipmentList(list);
    }

    public boolean isNull() {
        return this.values == null;
    }

    public RegisterEquipmentList add(final RegisterEquipment value) {
        final var list = new ArrayList<>(this.values);
        list.add(value);
        return new RegisterEquipmentList(list);
    }

    public RegisterEquipmentList remove(final int index) {
        final var list = new ArrayList<>(this.values);
        list.remove(index);
        return new RegisterEquipmentList(list);
    }
}
