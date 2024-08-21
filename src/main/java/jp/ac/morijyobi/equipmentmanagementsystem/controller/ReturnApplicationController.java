package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.ReturnApplicationForm;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IApplyDamageService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IApplyReturnService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IGetEquipmentService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IListEquipmentService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.implement.ApplyDamageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/return/application")
public class ReturnApplicationController {
    private final IListEquipmentService listEquipmentService;
    private final IApplyReturnService returnApplicationService;
    private final IApplyDamageService damageApplicationService;

    public ReturnApplicationController(
            IListEquipmentService listEquipmentService,
            IApplyReturnService returnApplicationService,
            ApplyDamageService damageApplicationService) {
        this.listEquipmentService = listEquipmentService;
        this.returnApplicationService = returnApplicationService;
        this.damageApplicationService = damageApplicationService;
    }

    @GetMapping()
    public String get(final Model model) {
//        final List<Equipment> lendingEquipmentList = listEquipmentService.executeLending();
//
//        final ReturnApplicationForm returnApplicationForm = ReturnApplicationForm.generate();
//
//        model.addAttribute(returnApplicationForm);
//        model.addAttribute(lendingEquipmentList);

        return "return/application/application";
    }

    @PostMapping()
    public String post(final @Validated ReturnApplicationForm returnApplicationForm,
                       final BindingResult bindingResult,
                       final Model model,
                       final @AuthenticationPrincipal UserDetails userDetails) {
//        if (bindingResult.hasErrors()) {
//            final String mail= userDetails.getUsername();
//            final List<Equipment> equipments = listEquipmentService.executeLending();
//            model.addAttribute("equipmentList", equipments);
//            return "return/application/application";
//        }
//
//        final int returnApplicationResult = returnApplicationService.execute(returnApplicationForm);
//        final int damageApplicationResult = damageApplicationService.execute(returnApplicationForm);
//
//        if (returnApplicationResult == 1 && damageApplicationResult == 1) return "redirect:/return/application/success";

        return "redirect:/return/application/failed";
    }

    @GetMapping("/success")
    public String success() {
        return "return/application/success";
    }

    @GetMapping("/failed")
    public String failed() {
        return "return/application/failed";
    }
}
