package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.EquipmentSearchCriteria;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IListEquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListEquipmentService implements IListEquipmentService {
    private final IEquipmentsMapper equipmentsMapper;

    public ListEquipmentService(final IEquipmentsMapper equipmentsMapper) {
        this.equipmentsMapper = equipmentsMapper;
    }

    @Override
    public List<Equipment> execute() {
        return this.equipmentsMapper.selectAll();
    }

    @Override
    public List<Equipment> search(final EquipmentSearchCriteria equipmentSearchCriteria) {
        return this.equipmentsMapper.selectBySearchCriteria(
                equipmentSearchCriteria.getName(),
                equipmentSearchCriteria.getEquipmentCategoryIdList(),
                equipmentSearchCriteria.getEquipmentStateList()
        );
    }

    @Override
    public List<Equipment> searchByIds(final List<Integer> equipmentIds) {
        return this.equipmentsMapper.selectByIds(equipmentIds);
    }
}
