package jp.ac.morijyobi.equipmentmanagementsystem.bean.dto;

import jakarta.validation.constraints.NotBlank;
import jp.ac.morijyobi.equipmentmanagementsystem.util.IntUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutEquipmentId implements Serializable {
    @NotBlank
    private String value;

    public static CheckoutEquipmentId empty() {
        return new CheckoutEquipmentId();
    }

    public int toInt() {
        return IntUtil.TryToInt(value);
    }
}
