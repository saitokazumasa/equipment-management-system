package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.CheckoutApplicationForm;
import jp.ac.morijyobi.equipmentmanagementsystem.service.ICheckoutApplicationService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IEquipmentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout/application")
public class CheckoutApplicationController {
    private final IEquipmentService equipmentsService;
    private final ICheckoutApplicationService checkoutApplicationsService;

    public CheckoutApplicationController(
            final IEquipmentService equipmentService,
            final ICheckoutApplicationService checkoutApplicationsService
    ) {
        this.equipmentsService = equipmentService;
        this.checkoutApplicationsService = checkoutApplicationsService;
    }

    @GetMapping()
    public String get(
            final @AuthenticationPrincipal UserDetails userDetails,
            final Model model
    ) {
        final String mail = userDetails.getUsername();

        model.addAttribute("checkoutApplicationForm", CheckoutApplicationForm.generate(mail));
        model.addAttribute("mail", mail);
        model.addAttribute("equipmentList", equipmentsService.fetchAll());
        return "checkout/application/application";
    }

    @PostMapping()
    public String post(
            final @Validated CheckoutApplicationForm checkoutApplicationForm,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("equipmentList", equipmentsService.fetchAll());
            return "checkout/application/application";
        }

        final int result = checkoutApplicationsService.execute(checkoutApplicationForm);

        if (result == 1) return "redirect:/checkout/application/success";

        return "redirect:/checkout/application/failed";
    }

    @GetMapping("/success")
    public String success() {
        return "checkout/application/success";
    }

    @GetMapping("/failed")
    public String failed() {
        return "checkout/application/failed";
    }
}
