package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApproval;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.service.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/checkout/approval")
public class ApproveCheckoutController {
    private final IGetCheckoutApprovalService getCheckoutApprovalService;
    private final IGetCheckoutService getCheckoutService;
    private final IGetAccountService getAccountService;
    private final IGetEquipmentService getEquipmentService;
    private final IApproveCheckoutService approveCheckoutService;

    private static class AttributeName {
        public static final String APPLICATOR_NAME = "applicatorName";
        public static final String EQUIPMENT = "equipment";
        public static final String CHECKOUT_ID = "checkoutId";
    }

    public ApproveCheckoutController(
            final IGetCheckoutApprovalService getCheckoutApprovalService,
            final IGetCheckoutService getCheckoutService,
            final IGetAccountService getAccountService,
            final IGetEquipmentService getEquipmentService,
            final IApproveCheckoutService approveCheckoutService
    ) {
        this.getCheckoutApprovalService = getCheckoutApprovalService;
        this.getCheckoutService = getCheckoutService;
        this.getAccountService = getAccountService;
        this.getEquipmentService = getEquipmentService;
        this.approveCheckoutService = approveCheckoutService;
    }

    @RequestMapping()
    public String get(final @RequestParam int checkoutId, final Model model) {
//        final CheckoutApproval checkoutApproval = getCheckoutApprovalService.executeByCheckoutApplicationId(checkoutId);
        final CheckoutApplication checkoutApplication =  getCheckoutService.execute(checkoutId);
        final Account applicator = getAccountService.executeById(checkoutApplication.getAccountId());
        final Equipment equipment = getEquipmentService.executeAvailableForLoanById(checkoutApplication.getEquipmentId());

//        if (checkoutApproval != null) return "redirect:/checkout/application";
        if (equipment == null) return "redirect:/checkout/application";

        model.addAttribute(AttributeName.APPLICATOR_NAME, applicator.getName());
        model.addAttribute(AttributeName.EQUIPMENT, equipment);
        model.addAttribute(AttributeName.CHECKOUT_ID, checkoutId);
        return "checkout/approval";
    }

    @PostMapping(params = "submit")
    public String submit(
            final @RequestParam int checkoutId,
            final @AuthenticationPrincipal UserDetails userDetails
    ) {
        final String mail = userDetails.getUsername();
        final CheckoutApplication checkoutApplication =  getCheckoutService.execute(checkoutId);

        try {
            approveCheckoutService.execute(mail, checkoutApplication);
            return "redirect:/checkout/approval/success";
        } catch (final Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/checkout/approval/failed";
        }
    }

    @PostMapping(params = "cancel")
    public String cancel() {
        return "redirect:/checkout/application";
    }

    @GetMapping("/success")
    public String success() {
        return "checkout/success_approval";
    }

    @GetMapping("/failed")
    public String failed() {
        return "checkout/failed_approval";
    }
}
