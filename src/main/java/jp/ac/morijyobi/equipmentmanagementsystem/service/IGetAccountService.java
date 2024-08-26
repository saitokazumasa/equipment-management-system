package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;

public interface IGetAccountService {
    public Account executeById(final int accountId);

    public Account executeByMail(final String mail);
}
