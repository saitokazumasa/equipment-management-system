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
public class EquipmentId implements Serializable {
    @NotBlank
    private String value;

    public static EquipmentId empty() {
        return new EquipmentId();
    }

    public int toInt() {
        return IntUtil.TryToInt(value);
    }
}
