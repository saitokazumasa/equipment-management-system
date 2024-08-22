package jp.ac.morijyobi.equipmentmanagementsystem.service.lost;

public interface IApplyLostService {
    public int execute(final String damageReason, final int checkoutApplicationId);
}
