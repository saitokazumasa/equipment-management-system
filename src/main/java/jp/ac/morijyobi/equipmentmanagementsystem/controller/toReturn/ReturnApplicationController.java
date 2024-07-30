package jp.ac.morijyobi.equipmentmanagementsystem.controller.toReturn;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.ReturnApplicationForm;
import jp.ac.morijyobi.equipmentmanagementsystem.service.damage.IApplyDamageService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.IFetchEquipmentService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.toReturn.IApplyReturnService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.damage.implement.ApplyDamageService;
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
    private final IFetchEquipmentService equipmentsService;
    private final IApplyReturnService returnApplicationService;
    private final IApplyDamageService damageApplicationService;

    public ReturnApplicationController(
            IFetchEquipmentService equipmentsService,
            IApplyReturnService returnApplicationService,
            ApplyDamageService damageApplicationService) {
        this.equipmentsService = equipmentsService;
        this.returnApplicationService = returnApplicationService;
        this.damageApplicationService = damageApplicationService;
    }

    @GetMapping()
    public String get(final Model model) {
        final List<Equipment> lendingEquipmentList = equipmentsService.executeLending();

        final ReturnApplicationForm returnApplicationForm = ReturnApplicationForm.generate();

        model.addAttribute(returnApplicationForm);
        model.addAttribute(lendingEquipmentList);

        return "return/application/application";
    }

    @PostMapping()
    public String post(final @Validated ReturnApplicationForm returnApplicationForm,
                       final BindingResult bindingResult,
                       final Model model,
                       final @AuthenticationPrincipal UserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            final String mail= userDetails.getUsername();
            final List<Equipment> equipments = equipmentsService.executeLending();
            model.addAttribute("equipmentList", equipments);
            return "return/application/application";
        }

        final int returnApplicationResult = returnApplicationService.execute(returnApplicationForm);
        final int damageApplicationResult = damageApplicationService.execute(returnApplicationForm);

        if (returnApplicationResult == 1 && damageApplicationResult == 1) return "redirect:/return/application/success";

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
