package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApproval;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApprovalsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IApproveCheckoutService;
import org.springframework.stereotype.Service;

@Service
public class ApproveCheckoutService implements IApproveCheckoutService {
    private final ICheckoutApprovalsMapper checkoutApprovalsMapper;

    public ApproveCheckoutService(ICheckoutApprovalsMapper checkoutApprovalsMapper) {
        this.checkoutApprovalsMapper = checkoutApprovalsMapper;
    }

    @Override
    public void execute(final int checkoutId, final int accountId) {
        final CheckoutApproval checkoutApproval = new CheckoutApproval();
        checkoutApproval.setCheckoutApplicationId(checkoutId);
        checkoutApproval.setAccountId(accountId);
        // 申請を承認
        checkoutApprovalsMapper.insert(checkoutApproval);
    }
}
