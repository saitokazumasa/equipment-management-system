package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.CheckoutIdList;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApproval;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApprovalsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IApproveCheckoutService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IGetAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApproveCheckoutService implements IApproveCheckoutService {
    private final ICheckoutApprovalsMapper checkoutApprovalsMapper;
    private final IGetAccountService getAccountService;

    public ApproveCheckoutService(ICheckoutApprovalsMapper checkoutApprovalsMapper, IGetAccountService getAccountService) {
        this.checkoutApprovalsMapper = checkoutApprovalsMapper;
        this.getAccountService = getAccountService;
    }

    @Override
    @Transactional
    public void execute(final CheckoutIdList checkoutIdList, final String mail) {
        final int accountId = getAccountService.executeByMail(mail).getId();

        for (final int checkoutId : checkoutIdList.getValues()) {
            final CheckoutApproval checkoutApproval = new CheckoutApproval(
                    -1,
                    checkoutId,
                    accountId,
                    LocalDateTime.now()
            );
            checkoutApprovalsMapper.insert(checkoutApproval);
        }
    }
}
