package jp.ac.morijyobi.equipmentmanagementsystem.service.impl;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IEquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService implements IEquipmentService {
    private final IEquipmentsMapper equipmentsMapper;
    private final IAccountsMapper accountsMapper;

    public EquipmentService(final IEquipmentsMapper equipmentsMapper, final IAccountsMapper accountsMapper) {
        this.equipmentsMapper = equipmentsMapper;
        this.accountsMapper = accountsMapper;
    }

    @Override
    public void register(final Equipment equipment) {
        equipmentsMapper.insert(equipment);
    }

    @Override
    public Equipment fetchById(final int id) {
        return equipmentsMapper.selectById(id);
    }

    @Override
    public List<Equipment> fetchAll() {
        return equipmentsMapper.selectAll();
    }

    @Override
    public List<Equipment> fetchLending(final String mail) {
        final Account account = accountsMapper.selectByMail(mail);

        return equipmentsMapper.selectLending(account.getId());
    }
}
