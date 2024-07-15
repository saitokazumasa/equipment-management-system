package jp.ac.morijyobi.equipmentmanagementsystem.bean.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.util.JsonUtil;
import lombok.Getter;

public class CheckoutApplicationForm {
    @Getter
    @NotBlank
    @Size(min = 1)
    private final String _json;

    private final JsonUtil<Equipment[]> _jsonUtil;

    public CheckoutApplicationForm(final String json) {
        this._json = json;
        this._jsonUtil = new JsonUtil<>(Equipment[].class);
    }

    public static CheckoutApplicationForm empty() {
        return new CheckoutApplicationForm("");
    }

    public Equipment[] equipments() {
        final Equipment[] equipments = _jsonUtil.tryToObject(_json);

        if (equipments.length == 0) throw new ArrayIndexOutOfBoundsException();

        return equipments;
    }
}
