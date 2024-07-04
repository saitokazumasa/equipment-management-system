package jp.ac.morijyobi.equipmentmanagementsystem.service.impl;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IEquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService implements IEquipmentService {
    private final IEquipmentsMapper equipmentsMapper;

    public EquipmentService(final IEquipmentsMapper equipmentsMapper) {
        this.equipmentsMapper = equipmentsMapper;
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
}
