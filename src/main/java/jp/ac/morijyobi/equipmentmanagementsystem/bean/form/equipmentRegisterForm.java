package jp.ac.morijyobi.equipmentmanagementsystem.bean.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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


    public @NotBlank @Size(min = 1, max = 256) String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(@NotBlank @Size(min = 1, max = 256) String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public @NotBlank String getCategory() { return category; }

    public void setCategory(@NotBlank String category) { this.category = category; }

    public @NotNull Boolean getIsPeriod() {
        return isPeriod;
    }

    public void setIsPeriod(@NotNull Boolean period) {
        isPeriod = period;
    }

    @NotNull
    @Min(1)
    public int getLendingPeriod() {
        return lendingPeriod;
    }

    public void setLendingPeriod(@NotNull @Min(1) int lendingPeriod) {
        this.lendingPeriod = lendingPeriod;
    }

    @NotNull
    @Min(1)
    public int getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(@NotNull @Min(1) int notificationDate) {
        this.notificationDate = notificationDate;
    }

    public @Size(max = 512) String getRemark() {
        return remark;
    }

    public void setRemark(@Size(max = 512) String remark) {
        this.remark = remark;
    }
}
