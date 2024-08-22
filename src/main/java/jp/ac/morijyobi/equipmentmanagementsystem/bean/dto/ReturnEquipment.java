package jp.ac.morijyobi.equipmentmanagementsystem.bean.dto;

import jakarta.validation.constraints.NotBlank;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnEquipment implements Serializable {
    @NotBlank
    private String equipmentId;

    private Equipment equipment;

    private boolean hasDamaged;

    private String damagedReason;

    public static ReturnEquipment empty() {
        return new ReturnEquipment();
    }
}
