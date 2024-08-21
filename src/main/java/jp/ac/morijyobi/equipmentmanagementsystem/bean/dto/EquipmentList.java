package jp.ac.morijyobi.equipmentmanagementsystem.bean.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class EquipmentList implements Serializable {
    @NotNull
    @NotEmpty
    private List<Equipment> values;

    public static EquipmentList empty() {
        final var list = new ArrayList<Equipment>();
        return new EquipmentList(list);
    }

    public boolean isNull() {
        return this.values == null;
    }

    public EquipmentList add(final Equipment value) {
        final var list = new ArrayList<>(this.values);
        list.add(value);
        return new EquipmentList(list);
    }

    public EquipmentList remove(final int index) {
        final var list = new ArrayList<>(this.values);
        list.remove(index);
        return new EquipmentList(list);
    }
}
