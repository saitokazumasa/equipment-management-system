package jp.ac.morijyobi.equipmentmanagementsystem.constant;

public enum AccountCategory {
    STUDENT("学生"),
    USER("利用者"),
    EQUIPMENT_MANAGER("備品管理者"),
    SYSTEM_MANAGER("システム管理者");

    private final String text;

    private AccountCategory(final String text) {
        this.text = text;
    }

    public String getString() {
        return this.text;
    }
}
