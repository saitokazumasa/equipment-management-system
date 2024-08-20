package jp.ac.morijyobi.equipmentmanagementsystem.bean.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Student;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.AccountCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.util.IntUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterStudentAccount implements Serializable {
    @Size(min = 1)
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String mail;

    private int courseId;

    @Size(min = 4, max = 4)
    private String admissionYear;

    @Size(min = 4, max = 4)
    private String graduationYear;

    public static RegisterStudentAccount empty() {
        return new RegisterStudentAccount();
    }

    public Account toAccount() {
        return new Account(
                -1,
                this.name,
                this.mail,
                // NOTE: 仮パスワード
                "morijyobi",
                AccountCategory.STUDENT,
                true
        );
    }

    public Student toStudent(final int accountId) {
        final int id = IntUtil.TryToInt(this.id);
        final int admissionYarn = IntUtil.TryToInt(this.admissionYear);
        final int graduationYarn = IntUtil.TryToInt(this.graduationYear);

        return new Student(
                id,
                accountId,
                this.courseId,
                admissionYarn,
                graduationYarn
        );
    }
}
