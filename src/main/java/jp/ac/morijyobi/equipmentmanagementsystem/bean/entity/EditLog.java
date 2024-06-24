package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditLog {
    private int id;
    private int equipmentId;
    private int accountId;
    private String reason;
    private JsonEquipment beforeEquipmentData;
    private JsonEquipment afterEquipmentData;
    private LocalDateTime createdAt;
}
