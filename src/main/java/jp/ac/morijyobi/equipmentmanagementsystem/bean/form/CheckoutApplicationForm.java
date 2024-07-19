package jp.ac.morijyobi.equipmentmanagementsystem.bean.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.util.JsonUtil;

public record CheckoutApplicationForm(
        @NotBlank @Size(min = 1) String json
) {
    public static CheckoutApplicationForm empty() {
        return new CheckoutApplicationForm("");
    }

    public Equipment[] equipments() {
        final Equipment[] equipments = JsonUtil.tryToObject(json, Equipment[].class);

        if (equipments.length == 0) throw new ArrayIndexOutOfBoundsException();

        return equipments;
    }
}
