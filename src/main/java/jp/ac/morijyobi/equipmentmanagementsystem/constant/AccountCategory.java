package jp.ac.morijyobi.equipmentmanagementsystem.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountCategory {
    STUDENT("学生"),
    USER("利用者"),
    EQUIPMENT_MANAGER("備品管理者"),
    SYSTEM_MANAGER("システム管理者");

    private final String text;
}
