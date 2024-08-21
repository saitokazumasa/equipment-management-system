package jp.ac.morijyobi.equipmentmanagementsystem.service;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.ReturnEquipmentList;

public interface IApplyReturnService {
    public void execute(final int equipmentId);
    public void execute(final ReturnEquipmentList returnEquipmentList) throws Exception;
}
