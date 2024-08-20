package jp.ac.morijyobi.equipmentmanagementsystem.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EquipmentState {
    AVAILABLE_FOR_LOAN("貸出可能"),
    ON_LOAN("貸出中"),
    NOT_AVAILABLE_FOR_LOAN("貸出不可");

    private final String text;
}
