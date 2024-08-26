package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;

public interface IGetAccountService {
    public Account executeByMail(final String mail);
}
