package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import java.time.LocalDateTime;

public class DamagedApplication {
    private int id;
    private final int checkoutApplicationId;
    private final String reason;
    private final DamagedCategory category;
    private final LocalDateTime createdAt;

    public DamagedApplication(final int id, final int checkoutApplicationId, final String reason, final DamagedCategory category, final LocalDateTime createdAt) {
        this.id = id;
        this.checkoutApplicationId = checkoutApplicationId;
        this.reason = reason;
        this.category = category;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getCheckoutApplicationId() {
        return checkoutApplicationId;
    }

    public String getReason() {
        return reason;
    }

    public DamagedCategory getCategory() {
        return category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }
}
