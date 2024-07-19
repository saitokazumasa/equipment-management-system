package jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.IFetchEquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchEquipmentService implements IFetchEquipmentService {
    private final IEquipmentsMapper equipmentsMapper;
    private final IAccountsMapper accountsMapper;

    public FetchEquipmentService(final IEquipmentsMapper equipmentsMapper, IAccountsMapper accountsMapper) {
        this.equipmentsMapper = equipmentsMapper;
        this.accountsMapper = accountsMapper;
    }

    @Override
    public Equipment executeById(final int id) {
        return this.equipmentsMapper.selectById(id);
    }

    @Override
    public List<Equipment> executeLending(String mail) {
        final Account account = accountsMapper.selectByMail(mail);

        return equipmentsMapper.selectLending(account.getId());
    }
}
