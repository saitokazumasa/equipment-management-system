package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonEquipment {
    private int id;
    private String name;
    private int categoryId;
    private int storageLocationId;
    private EquipmentState state;
    private int lendingPeriod;
    private int notificationDate;
    private String remark;
    private LocalDateTime createdAt;
}
