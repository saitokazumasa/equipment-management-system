package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.CheckoutIdList;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.service.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Stream;

@Controller
@RequestMapping("/checkout/approval")
public class ApproveCheckoutController {
    private final IGetCheckoutService getCheckoutService;
    private final IListCheckoutService listCheckoutService;
    private final IGetEquipmentService getEquipmentService;
    private final IListEquipmentService listEquipmentService;
    private final IGetAccountService getAccountService;
    private final IApproveCheckoutService approveCheckoutService;

    public ApproveCheckoutController(IGetCheckoutService getCheckoutService, IListCheckoutService listCheckoutService, IGetEquipmentService getEquipmentService, IListEquipmentService listEquipmentService, IGetAccountService getAccountService, IApproveCheckoutService approveCheckoutService) {
        this.getCheckoutService = getCheckoutService;
        this.listCheckoutService = listCheckoutService;
        this.getEquipmentService = getEquipmentService;
        this.listEquipmentService = listEquipmentService;
        this.getAccountService = getAccountService;
        this.approveCheckoutService = approveCheckoutService;
    }

    @RequestMapping()
    public String get(@RequestParam final List<Integer> checkoutId, final Model model) {
        final CheckoutApplication checkoutApplication = getCheckoutService.execute(checkoutId.get(0));

        final List<CheckoutApplication> checkoutApplications = listCheckoutService.execute(checkoutId);

        final List<Integer> checkoutIds = checkoutApplications.stream().map(CheckoutApplication::getId).toList();

        final List<Integer> equipmentIds = checkoutApplications.stream().map(CheckoutApplication::getEquipmentId).toList();
        final List<Equipment> equipments = listEquipmentService.searchByIds(equipmentIds);

        final Account account = getAccountService.executeById(checkoutApplication.getAccountId());

        final CheckoutIdList checkoutIdList = new CheckoutIdList(checkoutIds);

        model.addAttribute("checkoutIds", checkoutIds);
        model.addAttribute("equipments", equipments);
        model.addAttribute("checkoutIdList", checkoutIdList);
        model.addAttribute("account", account);

        return "checkout/approval";
    }

    @PostMapping(value = "", params = "approve")
    public String approve(@Validated final CheckoutIdList checkoutIdList,
                          @AuthenticationPrincipal final UserDetails userDetails) {
        final Account account = getAccountService.executeByMail(userDetails.getUsername());

        try {
            approveCheckoutService.execute(checkoutIdList, account.getMail());
            return "redirect:/checkout/approval/success";
        } catch (final Exception e) {
            System.out.println(e);
            return "redirect:/checkout/approval/failed";
        }
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
