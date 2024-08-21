package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterStudentAccount;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterStudentAccountList;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Student;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IStudentsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IRegisterStudentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterStudentService implements IRegisterStudentService {
    private final IAccountsMapper accountsMapper;
    private final IStudentsMapper studentsMapper;
    private final PasswordEncoder passwordEncoder;

    public RegisterStudentService(
            final IAccountsMapper accountsMapper,
            final IStudentsMapper studentsMapper,
            final PasswordEncoder passwordEncoder
    ) {
        this.accountsMapper = accountsMapper;
        this.studentsMapper = studentsMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void execute(final RegisterStudentAccountList registerStudentAccountList) {
        for (final RegisterStudentAccount registerStudentAccount : registerStudentAccountList.getValues()) {
            final Account account = registerStudentAccount.toAccount();
            final Account newAccount = cryptPassword(account);
            // NOTE: MyBatis により newAccount.id がオートインクリメント値に書き変わっている
            this.accountsMapper.insert(newAccount);

            final Student student = registerStudentAccount.toStudent(newAccount.id);
            this.studentsMapper.insert(student);
        }
    }

    private Account cryptPassword(final Account account) {
        final String password = this.passwordEncoder.encode(account.getPassword());

        return new Account(
                account.id,
                account.name,
                account.mail,
                password,
                account.category,
                account.isEnable
        );
    }
}
