package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import java.time.LocalDateTime;

public class CheckoutApplication {
    private int id;
    private final int equipmentId;
    private final int accountId;
    private final LocalDateTime createdAt;

    public CheckoutApplication(final int id, final int equipmentId, final int accountId, final LocalDateTime createdAt) {
        this.id = id;
        this.equipmentId = equipmentId;
        this.accountId = accountId;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getEquipmentId() {
        return equipmentId;
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
