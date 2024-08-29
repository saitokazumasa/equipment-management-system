package jp.ac.morijyobi.equipmentmanagementsystem.bean.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.StorageLocation;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditEquipment implements Serializable {
    @NotBlank
    private int id;

    @NotBlank
    @Size(max = 256)
    private String name;

    @NotBlank
    private String category;

    @NotBlank
    private String storageLocation;

    private Boolean hasLendingPeriod;

    @Min(1)
    private int lendingPeriod;

    private Boolean hasNotificationDate;

    @Min(1)
    private int notificationDate;

    @Size(max = 512)
    private String remark;

    @NotBlank
    @Size(max = 512)
    private String editReason;

    public static EditEquipment set(final Equipment equipment, final StorageLocation storageLocation, final EquipmentCategory equipmentCategory) {
        final boolean lendingPeriodState = equipment.getLendingPeriod() != -1;
        final boolean notificationDateState = equipment.getNotificationDate() != -1;

        return new EditEquipment(
                equipment.getId(),
                equipment.getName(),
                equipmentCategory.getName(),
                storageLocation.getName(),
                lendingPeriodState,
                equipment.getLendingPeriod(),
                notificationDateState,
                equipment.getNotificationDate(),
                equipment.getRemark(),
                ""
        );
    }

    public Equipment toEquipment(
            final int categoryId,
            final int storageLocationId,
            final LocalDateTime createdAt
    ) {
        return new Equipment(
                -1,
                this.name,
                categoryId,
                storageLocationId,
                EquipmentState.AVAILABLE_FOR_LOAN,
                hasLendingPeriod ? this.lendingPeriod : -1,
                hasNotificationDate ? this.notificationDate : -1,
                this.remark,
                createdAt
        );
    }
}
