package jp.ac.morijyobi.equipmentmanagementsystem.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    NOT_EXIST_VALUE("存在しない値が指定されました");

    private final String text;
}
