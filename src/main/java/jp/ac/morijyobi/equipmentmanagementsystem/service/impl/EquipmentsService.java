package jp.ac.morijyobi.equipmentmanagementsystem.service.impl;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IEquipmentsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentsService implements IEquipmentsService {
    private final IEquipmentsMapper equipmentsMapper;

    public EquipmentsService(final IEquipmentsMapper equipmentsMapper) {
        this.equipmentsMapper = equipmentsMapper;
    }

    @Override
    public void insert(final Equipment equipment) {
        equipmentsMapper.insert(equipment);
    }

    @Override
    public Equipment selectById(final int id) {
        return equipmentsMapper.selectById(id);
    }

    @Override
    public List<Equipment> selectAll() {
        return equipmentsMapper.selectAll();
    }
}
