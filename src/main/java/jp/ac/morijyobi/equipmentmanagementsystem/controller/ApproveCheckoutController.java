package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
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

import java.util.Date;

@Controller
@RequestMapping("/checkout/approval")
public class ApproveCheckoutController {
    private final IGetCheckoutService getCheckoutService;
    private final IGetEquipmentService getEquipmentService;
    private final IGetAccountService getAccountService;
    private final IApproveCheckoutService approveCheckoutService;

    public ApproveCheckoutController(IGetCheckoutService getCheckoutService, IGetEquipmentService getEquipmentService, IGetAccountService getAccountService, IApproveCheckoutService approveCheckoutService) {
        this.getCheckoutService = getCheckoutService;
        this.getEquipmentService = getEquipmentService;
        this.getAccountService = getAccountService;
        this.approveCheckoutService = approveCheckoutService;
    }

    @RequestMapping()
    public String get(@RequestParam final int checkoutId, final Model model) {
        // 対象の申請を取得
        final CheckoutApplication checkoutApplication = getCheckoutService.execute(checkoutId);

        System.out.println("checkoutApplication: " + checkoutApplication);

        final Equipment equipment = getEquipmentService.executeById(checkoutApplication.getEquipmentId());
        final Account account = getAccountService.executeById(checkoutApplication.getAccountId());

        model.addAttribute("checkoutApplication", checkoutApplication);
        model.addAttribute("equipment", equipment);
        model.addAttribute("account", account);

        return "checkout/approval";
    }

    @PostMapping(value = "", params = "approve")
    public String approve(@RequestParam final int id,
                          @AuthenticationPrincipal final UserDetails userDetails) {
        final Account account = getAccountService.executeByMail(userDetails.getUsername());

        // 申請を承認
        try {
            approveCheckoutService.execute(id, account.getId());
        } catch (Exception e) {
            return "redirect:/checkout/approval/failed";
        }

        return "redirect:/checkout/approval/success";
    }

    @PostMapping(value = "", params = "cancel")
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
