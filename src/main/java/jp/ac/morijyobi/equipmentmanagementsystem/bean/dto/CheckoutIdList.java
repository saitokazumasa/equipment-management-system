package jp.ac.morijyobi.equipmentmanagementsystem.bean.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutIdList {
    @NotEmpty
    private List<Integer> values;

    public static CheckoutIdList empty() {
        return new CheckoutIdList();
    }

    public boolean isNull() {
        return this.values == null;
    }

    public CheckoutIdList add(final Integer value) {
        final var list = this.values;
        list.add(value);
        return new CheckoutIdList(list);
    }

    public CheckoutIdList remove(final int index) {
        final var list = this.values;
        list.remove(index);
        return new CheckoutIdList(list);
    }
}
