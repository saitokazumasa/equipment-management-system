package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import jp.ac.morijyobi.equipmentmanagementsystem.constant.DamagedCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DamagedApplication {
    private int id;
    private int checkoutApplicationId;
    private String reason;
    private DamagedCategory category;
    private LocalDateTime createdAt;
}
