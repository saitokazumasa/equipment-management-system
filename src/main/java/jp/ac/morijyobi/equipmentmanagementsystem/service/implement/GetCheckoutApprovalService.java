package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApproval;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICheckoutApprovalsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IGetCheckoutApprovalService;
import org.springframework.stereotype.Service;

@Service
public class GetCheckoutApprovalService implements IGetCheckoutApprovalService {
    private final ICheckoutApprovalsMapper checkoutApprovalsMapper;

    public GetCheckoutApprovalService (final ICheckoutApprovalsMapper checkoutApprovalsMapper) {
        this.checkoutApprovalsMapper = checkoutApprovalsMapper;
    }

    @Override
    public CheckoutApproval executeByCheckoutApplicationId(final int id) {
        return this.checkoutApprovalsMapper.selectByCheckoutApplicationId(id);
    }
}
