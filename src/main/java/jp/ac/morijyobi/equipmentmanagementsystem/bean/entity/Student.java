package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

public class Student {
    public int id;
    public final int account_id;
    public final int course_id;
    public final int admission_year;
    public final int graduation_year;

    public Student(final int id, final int account_id, final int course_id, final int admission_year, final int graduation_year) {
        this.id = id;
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

    public void setId(final int id) {
        this.id = id;
    }
}
