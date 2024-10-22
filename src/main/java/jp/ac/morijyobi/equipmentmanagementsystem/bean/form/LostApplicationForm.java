package jp.ac.morijyobi.equipmentmanagementsystem.bean.form;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LostApplicationForm {
    @NotBlank
    @Size(min = 1, max = 512)
    private String damageReason;

    @NotBlank
    private String checkoutApplicationId;
}
