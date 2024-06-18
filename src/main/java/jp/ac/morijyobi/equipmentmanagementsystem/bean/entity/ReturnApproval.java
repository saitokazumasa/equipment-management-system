package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnApproval {
    private int id;
    private int returnApplicationId;
    private int accountId;
    private LocalDateTime createdAt;
}
