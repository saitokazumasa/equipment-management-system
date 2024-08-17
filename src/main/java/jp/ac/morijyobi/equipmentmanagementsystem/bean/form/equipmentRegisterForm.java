package jp.ac.morijyobi.equipmentmanagementsystem.bean.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class equipmentRegisterForm {
    @NotBlank
    @Size(min = 1, max = 256)
    private String equipmentName;

    @NotBlank
    private String category;

    @NotNull
    private Boolean isPeriod;

    @NotNull
    @Min(1)
    private int lendingPeriod;

    @NotNull
    @Min(1)
    private int notificationDate;

    @Size(max = 512)
    private String remark;

    public equipmentRegisterForm() {
        this.lendingPeriod = 7;
        this.notificationDate = 3;
    }
}
