package jp.ac.morijyobi.equipmentmanagementsystem.bean.dto;

import jakarta.validation.constraints.NotBlank;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutEquipment implements Serializable {
    @NotBlank
    private String equipmentId;

    private Equipment equipment;

    private LocalDate returnDate;

    public static CheckoutEquipment empty() {
        return new CheckoutEquipment();
    }
}
