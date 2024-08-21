package jp.ac.morijyobi.equipmentmanagementsystem.service;

public interface IApplyDamagedService {
    public void execute(final String damagedReason, final int checkoutApplicationId);
}
