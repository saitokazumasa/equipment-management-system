package jp.ac.morijyobi.equipmentmanagementsystem.service;

public interface IApproveCheckoutService {
    public void execute(final int checkoutId, final int accountId);
}
