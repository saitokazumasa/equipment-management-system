package jp.ac.morijyobi.equipmentmanagementsystem.service;

import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;

public interface IUpdateEquipmentService {
    public int executeToState(final int equipmentId, EquipmentState state);
}
