package jp.ac.morijyobi.equipmentmanagementsystem.service.impl;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.EquipmentsService;
import org.springframework.stereotype.Service;

@Service
public class EquipmentsImpl implements EquipmentsService {
    private final IEquipmentsMapper equipmentsMapper;

    public EquipmentsImpl(final IEquipmentsMapper equipmentsMapper) {
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
}
