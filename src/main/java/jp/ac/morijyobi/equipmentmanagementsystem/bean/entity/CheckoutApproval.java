package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutApproval {
    private int id;
    private int checkoutApplicationId;
    private int accountId;
    private LocalDateTime createdAt;
}
