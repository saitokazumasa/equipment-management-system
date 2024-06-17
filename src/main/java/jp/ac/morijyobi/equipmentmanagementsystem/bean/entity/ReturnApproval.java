package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import java.time.LocalDateTime;

public class ReturnApproval {
    private int id;
    private final int returnApplicationId;
    private final int accountId;
    private final LocalDateTime createdAt;

    public ReturnApproval(final int id, final int returnApplicationId, final int accountId, final LocalDateTime createdAt) {
        this.id = id;
        this.returnApplicationId = returnApplicationId;
        this.accountId = accountId;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getReturnApplicationId() {
        return returnApplicationId;
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
