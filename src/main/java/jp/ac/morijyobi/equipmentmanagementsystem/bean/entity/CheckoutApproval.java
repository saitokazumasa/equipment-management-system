package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import java.time.LocalDateTime;

public class CheckoutApproval {
    private int id;
    private final int checkoutApplicationId;
    private final int accountId;
    private final LocalDateTime createdAt;

    public CheckoutApproval(final int id, final int checkoutApplicationId, final int accountId, final LocalDateTime createdAt) {
        this.id = id;
        this.checkoutApplicationId = checkoutApplicationId;
        this.accountId = accountId;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getCheckoutApplicationId() {
        return checkoutApplicationId;
    }

    public int getAccountId() {
        return accountId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(final int id) {
        this.id = id;
    }
}
