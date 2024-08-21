package jp.ac.morijyobi.equipmentmanagementsystem.bean.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ReturnEquipmentList implements Serializable {
    @NotNull
    @NotEmpty
    private List<ReturnEquipment> values;

    public static ReturnEquipmentList empty() {
        final var list = new ArrayList<ReturnEquipment>();
        return new ReturnEquipmentList(list);
    }

    public boolean isNull() {
        return this.values == null;
    }

    public ReturnEquipmentList add(final ReturnEquipment value) {
        final var list = new ArrayList<>(this.values);
        list.add(value);
        return new ReturnEquipmentList(list);
    }

    public ReturnEquipmentList remove(final int index) {
        final var list = new ArrayList<>(this.values);
        list.remove(index);
        return new ReturnEquipmentList(list);
    }
}
