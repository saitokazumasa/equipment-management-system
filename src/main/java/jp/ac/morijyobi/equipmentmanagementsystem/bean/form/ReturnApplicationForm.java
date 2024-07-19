package jp.ac.morijyobi.equipmentmanagementsystem.bean.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Damage;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.util.JsonUtil;

import java.util.List;

public class ReturnApplicationForm {
    @NotBlank
    @Size(min = 1)
    private final String json;

    private final String damageList;

    @NotBlank
    private final String mail;

    public ReturnApplicationForm(final String json, final String damageList, final String mail) {
        this.json = json;
        this.damageList = damageList;
        this.mail = mail;
    }

    public static ReturnApplicationForm generate(final String mail) {
        return new ReturnApplicationForm("", "", mail);
    }

    public String json() {
        return json;
    }

    public String damageList() { return damageList; }

    public String mail() {
        return mail;
    }

    public Equipment[] equipments() {
        final var equipments = JsonUtil.tryToObject(json, Equipment[].class);

        if (equipments.length == 0) throw new ArrayIndexOutOfBoundsException();

        return equipments;
    }

    public Damage[] damages() {
        final var damages = JsonUtil.tryToObject(damageList, Damage[].class);

        if (damages.length == 0) throw new ArrayIndexOutOfBoundsException();

        return damages;
    }
}
