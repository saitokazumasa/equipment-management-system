package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IGetEquipmentService;
import org.springframework.stereotype.Service;

@Service
public class GetEquipmentService implements IGetEquipmentService {
    private final IEquipmentsMapper equipmentsMapper;

    public GetEquipmentService(final IEquipmentsMapper equipmentsMapper) {
        this.equipmentsMapper = equipmentsMapper;
    }

    @Override
    public Equipment executeById(final int id) {
        return this.equipmentsMapper.selectById(id);
    }

    @Override
    public Equipment executeOnLoanById(final int id) {
        return this.equipmentsMapper.selectOnLoanById(id);
    }

    @Override
    public Equipment executeAvailableForLoanById(final int id) {
        return this.equipmentsMapper.selectAvailableForLoanById(id);
    }
}
