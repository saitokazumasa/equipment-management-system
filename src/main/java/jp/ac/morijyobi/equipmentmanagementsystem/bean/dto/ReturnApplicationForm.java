package jp.ac.morijyobi.equipmentmanagementsystem.bean.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Damage;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.util.JsonUtil;

public class ReturnApplicationForm {
    @NotBlank
    @Size(min = 1)
    private final String jsonEquipment;

    private final String damageList;

    public ReturnApplicationForm(final String jsonEquipment, final String damageList) {
        this.jsonEquipment = jsonEquipment;
        this.damageList = damageList;
    }

    public static ReturnApplicationForm generate() {
        return new ReturnApplicationForm("", "");
    }

    public String json() {
        return jsonEquipment;
    }

    public String damageList() { return damageList; }

    public Equipment[] equipments() {
        final var equipments = JsonUtil.tryToObject(jsonEquipment, Equipment[].class);

        if (equipments.length == 0) throw new ArrayIndexOutOfBoundsException();

        return equipments;
    }

    public Damage[] damages() {
        final var damages = JsonUtil.tryToObject(damageList, Damage[].class);

        if (damages.length == 0) throw new ArrayIndexOutOfBoundsException();

        return damages;
    }
}
