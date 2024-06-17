package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DamagedApproval {
    private int id;
    private int damagedApplicationId;
    private int accountId;
    private LocalDateTime createdAt;
}
