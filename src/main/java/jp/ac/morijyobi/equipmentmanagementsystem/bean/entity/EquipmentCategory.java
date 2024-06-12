package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

public class EquipmentCategory {
    private int id;
    private final String name;

    public EquipmentCategory(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(final int id) {
        this.id = id;
    }
}
