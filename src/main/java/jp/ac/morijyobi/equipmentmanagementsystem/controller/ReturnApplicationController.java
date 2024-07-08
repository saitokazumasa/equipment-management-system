package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.CheckoutApplicationForm;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.ReturnApplicationForm;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IEquipmentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/return/application")
public class ReturnApplicationController {
    private final IEquipmentService equipmentsService;

    public ReturnApplicationController(IEquipmentService equipmentsService) {
        this.equipmentsService = equipmentsService;
    }

    @GetMapping()
    public String get(final Model model,
                      final @AuthenticationPrincipal UserDetails userDetails) {
        final String mail= userDetails.getUsername();
        final List<Equipment> equipments = equipmentsService.fetchLending(mail);

        model.addAttribute("returnApplicationForm", ReturnApplicationForm.generate(mail));
        model.addAttribute("mail", mail);
        model.addAttribute("equipmentList", equipments);

        return "return/application/application";
    }

    @PostMapping()
    public String post(final @Validated CheckoutApplicationForm returnApplicationForm,
                       final BindingResult bindingResult,
                       final Model model,
                       final @AuthenticationPrincipal UserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            final String mail= userDetails.getUsername();
            final List<Equipment> equipments = equipmentsService.fetchLending(mail);
            model.addAttribute("equipmentList", equipments);
            return "return/application/application";
        }

        // TODO: 返却申請処理を実装する（DB）

        System.out.println(returnApplicationForm);

        return "redirect:/return/application";
    }
}
