package jp.ac.morijyobi.equipmentmanagementsystem.controller.lost;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.DamagedApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.LostApplicationForm;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.damage.IApplyDamageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.service.checkout.IFetchCheckoutService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.IFetchEquipmentService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static jp.ac.morijyobi.equipmentmanagementsystem.constant.DamagedCategory.LOST;

@Controller
@RequestMapping("/lost/application")
public class LostApplicationController {
    private final IFetchCheckoutService fetchCheckoutService;
    private final IFetchEquipmentService fetchEquipmentService;
    private final IApplyDamageService applyDamageService;
    private final IAccountsMapper IAccountsMapper;

    public LostApplicationController(IFetchCheckoutService fetchCheckoutService, IFetchEquipmentService fetchEquipmentService, IApplyDamageService applyDamageService, jp.ac.morijyobi.equipmentmanagementsystem.mapper.IAccountsMapper iAccountsMapper) {
        this.fetchCheckoutService = fetchCheckoutService;
        this.fetchEquipmentService = fetchEquipmentService;
        this.applyDamageService = applyDamageService;
        this.IAccountsMapper = iAccountsMapper;
    }

    @GetMapping("")
    public String index(Model model,
                        @RequestParam int equipmentId,
                        @ModelAttribute("form")LostApplicationForm form,
                        @AuthenticationPrincipal UserDetails userDetails) {
        if (equipmentId < 1) {
            // TODO:遷移先を変更する
            return "redirect:/checkout/application";
        }

        final Account account = IAccountsMapper.selectByMail(userDetails.getUsername());
        CheckoutApplication checkoutApplication = fetchCheckoutService.executeByEquipmentIdAndAccountId(equipmentId, account.getId());;

        if (checkoutApplication == null) {
            // TODO:遷移先を変更する
            return "redirect:/checkout/application";
        }

        final Equipment equipment = fetchEquipmentService.executeById(checkoutApplication.getEquipmentId());
        form.setCheckoutApplicationId(String.valueOf(checkoutApplication.getId()));

        model.addAttribute("equipment", equipment);
        model.addAttribute("form", form);
        model.addAttribute("equipmentId", equipmentId);

        return "lost/application/application";
    }

    @PostMapping("")
    public String register(@Validated LostApplicationForm form,
                           @RequestParam int equipmentId,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/lost/application?equipmentId=" + equipmentId;
        }

        Equipment equipment = fetchEquipmentService.executeById(equipmentId);

        model.addAttribute("equipment", equipment);
        model.addAttribute("form", form);
        model.addAttribute("equipmentId", equipmentId);

        return "lost/application/confirmation";
    }

    @PostMapping(value="confirm", params = "complete")
    public String complete(@Validated LostApplicationForm form,
                           @RequestParam int equipmentId,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/lost/application/failed";
        }

        final int result = applyDamageService.execute(
                form.getDamageReason(),
                Integer.parseInt(form.getCheckoutApplicationId()),
                LOST
        );

        if (result == 1) {
            return "redirect:/lost/application/success";
        } else {
            return "redirect:/lost/application/failed";
        }
    }

    @PostMapping(value="confirm", params = "edit")
    public String edit(@Validated LostApplicationForm form,
                       @RequestParam int equipmentId,
                       RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("form", form);
        return "redirect:/lost/application?equipmentId=" + equipmentId;
    }

}
