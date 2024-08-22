package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterEquipmentList;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterEquipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.StorageLocation;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IEquipmentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IRegisterEquipmentService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IRegisterEquipmentCategoryService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IRegisterStorageLocationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegisterEquipmentService implements IRegisterEquipmentService {
    private final IRegisterEquipmentCategoryService registerEquipmentCategoryService;
    private final IRegisterStorageLocationService registerStorageLocationService;
    private final IEquipmentsMapper equipmentsMapper;

    public RegisterEquipmentService(
            final IRegisterEquipmentCategoryService registerEquipmentCategoryService,
            final IRegisterStorageLocationService registerStorageLocationService,
            final IEquipmentsMapper equipmentsMapper
    ) {
        this.registerEquipmentCategoryService = registerEquipmentCategoryService;
        this.registerStorageLocationService = registerStorageLocationService;
        this.equipmentsMapper = equipmentsMapper;
    }

    @Override
    @Transactional
    public void execute(final RegisterEquipmentList registerEquipmentList) {
        for (final RegisterEquipment registerEquipment : registerEquipmentList.getValues()) {
            final EquipmentCategory category =
                    this.registerEquipmentCategoryService.execute(registerEquipment.getCategory());
            final StorageLocation storageLocation =
                    this.registerStorageLocationService.execute(registerEquipment.getStorageLocation());

            final Equipment equipment = registerEquipment.toEquipment(
                    category.getId(),
                    storageLocation.getId(),
                    LocalDateTime.now()
            );

            this.equipmentsMapper.insert(equipment);
        }
    }
}
