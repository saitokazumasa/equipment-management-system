package jp.ac.morijyobi.equipmentmanagementsystem.bean.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Damage;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;

import java.util.List;

public class ReturnApplicationForm {
    @NotBlank
    @Size(min = 1)
    private final String json;

    private final String damageList;

    @NotBlank
    private final String mail;

    private final ObjectMapper objectMapper;

    public ReturnApplicationForm(final String json, final String damageList, final String mail) {
        this.json = json;
        this.damageList = damageList;
        this.mail = mail;
        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
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
        final Equipment[] equipments = tryToArray(json);

        if (equipments.length == 0) throw new ArrayIndexOutOfBoundsException();

        return equipments;
    }

    private Equipment[] tryToArray(final String json) {
        try {
            return objectMapper.readValue(json, Equipment[].class);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Damage[] damages() {
        final Damage[] damages = tryToDamageArray(damageList);

        if (damages.length == 0) throw new ArrayIndexOutOfBoundsException();

        return damages;
    }

    private Damage[] tryToDamageArray(final String json) {
        try {
            return objectMapper.readValue(json, Damage[].class);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
