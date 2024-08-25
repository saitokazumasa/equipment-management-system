package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IGetAccountService;
import org.springframework.stereotype.Service;

@Service
public class GetAccountService implements IGetAccountService {
    private final IAccountsMapper accountsMapper;

    public GetAccountService(final IAccountsMapper accountsMapper) {
        this.accountsMapper = accountsMapper;
    }

    @Override
    public Account executeByMail(String mail) {
        return accountsMapper.selectByMail(mail);
    }
}
