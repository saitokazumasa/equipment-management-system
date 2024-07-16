package jp.ac.morijyobi.equipmentmanagementsystem.controller.checkout;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.CheckoutApplicationForm;
import jp.ac.morijyobi.equipmentmanagementsystem.service.checkout.IApplyCheckoutService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.IListEquipmentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/checkout/application")
public class CheckoutApplicationController {
    private final IListEquipmentService listEquipmentService;
    private final IApplyCheckoutService checkoutApplicationsService;

    public CheckoutApplicationController(
            final IListEquipmentService listEquipmentService,
            final IApplyCheckoutService checkoutApplicationsService
    ) {
        this.listEquipmentService = listEquipmentService;
        this.checkoutApplicationsService = checkoutApplicationsService;
    }

    @GetMapping()
    public String get(
            final @AuthenticationPrincipal UserDetails userDetails,
            final Model model
    ) throws Exception {
        final String mail = userDetails.getUsername();
        final CheckoutApplicationForm checkoutApplicationForm = CheckoutApplicationForm.empty();
        final List<Equipment> equipmentList = listEquipment();

        model.addAttribute(mail);
        model.addAttribute(checkoutApplicationForm);
        model.addAttribute(equipmentList);

        return "checkout/application/application";
    }

    @PostMapping()
    public String post(
            final @AuthenticationPrincipal UserDetails userDetails,
            final @Validated CheckoutApplicationForm checkoutApplicationForm,
            final BindingResult bindingResult,
            final Model model
    ) throws Exception {
        final String mail = userDetails.getUsername();

        if (bindingResult.hasErrors()) {
            final List<Equipment> equipmentList = listEquipment();

            model.addAttribute(mail);
            model.addAttribute(equipmentList);

            return "checkout/application/application";
        }

        final int result = this.checkoutApplicationsService.execute(mail, checkoutApplicationForm);

        if (result == 1) return "redirect:/checkout/application/success";

        return "redirect:/checkout/application/failed";
    }

    public List<Equipment> listEquipment() throws Exception {
        final List<Equipment> list = this.listEquipmentService.execute();

        if (list == null) throw new Exception("equipmentList is null");
        if (list.isEmpty()) throw new Exception("equipmentList is empty");

        return list;
    }
}
