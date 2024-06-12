package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

public class Student {
    public int id;
    public int account_id;
    public int course_id;
    public int admission_year;
    public int graduation_year;

    public Student(int account_id, int course_id, int admission_year, int graduation_year) {
        this.account_id = account_id;
        this.course_id = course_id;
        this.admission_year = admission_year;
        this.graduation_year = graduation_year;
    }

    public int getId() {
        return id;
    }

    public int getAccountId() {
        return account_id;
    }

    public int getCourseId() {
        return course_id;
    }

    public int getAdmissionYear() {
        return admission_year;
    }

    public int getGraduationYear() {
        return graduation_year;
    }

    public void setAccountId(int account_id) {
        this.account_id = account_id;
    }

    public void setCourseId(int course_id) {
        this.course_id = course_id;
    }

    public void setAdmissionYear(int admission_year) {
        this.admission_year = admission_year;
    }

    public void setGraduationYear(int graduation_year) {
        this.graduation_year = graduation_year;
    }
}
