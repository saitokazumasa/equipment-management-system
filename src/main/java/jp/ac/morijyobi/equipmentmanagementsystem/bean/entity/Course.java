package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

public class Course {
    private int id;
    private final String name;

    public Course(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
