package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import java.time.LocalDateTime;

public class ReturnApplication {
    private int id;
    private final int checkoutApplicationId;
    private final LocalDateTime createdAt;

    public ReturnApplication(final int id, final int checkoutApplicationId, final LocalDateTime createdAt) {
        this.id = id;
        this.checkoutApplicationId = checkoutApplicationId;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getCheckoutApplicationId() {
        return checkoutApplicationId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(final int id) {
        this.id = id;
    }
}
