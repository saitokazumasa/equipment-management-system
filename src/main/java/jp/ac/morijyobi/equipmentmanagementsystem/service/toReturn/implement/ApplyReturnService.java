package jp.ac.morijyobi.equipmentmanagementsystem.service.toReturn.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.ReturnApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.ReturnApplicationForm;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IReturnApplicationsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.toReturn.IApplyReturnService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ApplyReturnService implements IApplyReturnService {
    private final IReturnApplicationsMapper returnApplicationsMapper;
    private final IAccountsMapper accountsMapper;
    private final ICheckoutApplicationsMapper checkoutApplicationsMapper;

    public ApplyReturnService(
            final IReturnApplicationsMapper returnApplicationsMapper,
            final IAccountsMapper accountsMapper,
            final ICheckoutApplicationsMapper checkoutApplicationsMapper) {
        this.returnApplicationsMapper = returnApplicationsMapper;
        this.accountsMapper = accountsMapper;
        this.checkoutApplicationsMapper = checkoutApplicationsMapper;
    }

    @Override
    @Transactional
    public int execute(ReturnApplicationForm returnApplicationForm) {
        System.out.println("ログイン中：" + SecurityContextHolder.getContext().getAuthentication().getName());

        // TODO
        final Account account = accountsMapper.selectByMail("kawaguchi@gmail.com");

        for (final Equipment equipment : returnApplicationForm.equipments()) {
            final CheckoutApplication checkoutApplication = checkoutApplicationsMapper.selectNotReturned(account.getId(), equipment.getId());
            final var returnApplication = new ReturnApplication(
                    -1,
                    checkoutApplication.getId(),
                    LocalDateTime.now()
            );

            final int result = returnApplicationsMapper.insert(returnApplication);

            if (result != 1) return result;
        }

        return 1;
    }
}
