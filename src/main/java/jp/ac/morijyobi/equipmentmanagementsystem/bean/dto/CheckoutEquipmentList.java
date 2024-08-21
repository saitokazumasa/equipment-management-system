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
public class CheckoutEquipmentList implements Serializable {
    @NotNull
    @NotEmpty
    private List<Equipment> values;

    public static CheckoutEquipmentList empty() {
        final var list = new ArrayList<Equipment>();
        return new CheckoutEquipmentList(list);
    }

    public boolean isNull() {
        return this.values == null;
    }

    public CheckoutEquipmentList add(final Equipment value) {
        final var list = new ArrayList<>(this.values);
        list.add(value);
        return new CheckoutEquipmentList(list);
    }

    public CheckoutEquipmentList remove(final int index) {
        final var list = new ArrayList<>(this.values);
        list.remove(index);
        return new CheckoutEquipmentList(list);
    }
}
