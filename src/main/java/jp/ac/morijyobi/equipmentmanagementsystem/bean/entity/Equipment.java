package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import java.time.LocalDateTime;

public class Equipment {
    private int id;
    private final String name;
    private final int categoryId;
    private final int storageLocationId;
    private final EquipmentState state;
    private final int lendingPeriod;
    private final int notificationDate;
    private final String remark;
    private final LocalDateTime createdAt;

    public Equipment(final int id, final String name, final int categoryId, final int storageLocationId, final EquipmentState state, final int lendingPeriod, final int notificationDate, final String remark, final LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.storageLocationId = storageLocationId;
        this.state = state;
        this.lendingPeriod = lendingPeriod;
        this.notificationDate = notificationDate;
        this.remark = remark;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getStorageLocationId() {
        return storageLocationId;
    }

    public EquipmentState getState() {
        return state;
    }

    public int getLendingPeriod() {
        return lendingPeriod;
    }

    public int getNotificationDate() {
        return notificationDate;
    }

    public String getRemark() {
        return remark;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(final int id) {
        this.id = id;
    }
}
