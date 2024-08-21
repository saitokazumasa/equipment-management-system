package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.EquipmentId;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.EquipmentList;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.ErrorMessage;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IApplyCheckoutService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IGetEquipmentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/checkout/application")
public class ApplyCheckoutController {
    private final IGetEquipmentService getEquipmentService;
    private final IApplyCheckoutService applyCheckoutService;

    private static class AttributeName {
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String EQUIPMENT_ID = "equipmentId";
        public static final String EQUIPMENT_LIST = "equipmentList";
    }

    public ApplyCheckoutController(
            final IGetEquipmentService getEquipmentService,
            final IApplyCheckoutService applyCheckoutService
    ) {
        this.getEquipmentService = getEquipmentService;
        this.applyCheckoutService = applyCheckoutService;
    }

    @GetMapping()
    public String get(final Model model) {
        // リダイレクトの場合はリセットしない
        final boolean isRedirected = model.containsAttribute(AttributeName.EQUIPMENT_LIST);
        final EquipmentList equipmentList = isRedirected ?
                (EquipmentList) model.getAttribute(AttributeName.EQUIPMENT_LIST) :
                EquipmentList.empty();

        model.addAttribute(AttributeName.EQUIPMENT_ID, EquipmentId.empty());
        model.addAttribute(AttributeName.EQUIPMENT_LIST, equipmentList);
        return "checkout/application";
    }

    @PostMapping(params = "add")
    public String add(
            final @Validated EquipmentId equipmentId,
            final BindingResult bindingResult,
            final @Validated EquipmentList equipmentList,
            // これがないと初期状態の空リストを通せない
            final BindingResult __,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) return "checkout/application";

        final Equipment equipment = this.getEquipmentService.executeById(equipmentId.toInt());
        if (equipment == null) {
            model.addAttribute(AttributeName.ERROR_MESSAGE, ErrorMessage.NOT_EXIST_VALUE.getText());
            return "checkout/application";
        }

        // values が null の状態で入ってくることがあるため、その場合は初期化してから追加する
        final EquipmentList newEquipmentList = equipmentList.isNull() ?
                EquipmentList.empty().add(equipment) :
                equipmentList.add(equipment);

        redirectAttributes.addFlashAttribute(AttributeName.EQUIPMENT_LIST, newEquipmentList);
        return "redirect:/checkout/application";
    }

    @PostMapping(params = "remove")
    public String remove(
            final @Validated EquipmentId equipmentId,
            final BindingResult _1,
            final @Validated EquipmentList equipmentList,
            final BindingResult _2,
            final HttpServletRequest request,
            final RedirectAttributes redirectAttributes
    ) {
        final int index = Integer.parseInt(request.getParameter("remove"));
        final EquipmentList newEquipmentList = equipmentList.remove(index);

        redirectAttributes.addFlashAttribute(AttributeName.EQUIPMENT_LIST, newEquipmentList);
        return "redirect:/checkout/application";
    }

    @PostMapping(params = "submit")
    public String submit(
            final @AuthenticationPrincipal UserDetails userDetails,
            final @Validated EquipmentList equipmentList,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AttributeName.EQUIPMENT_ID, EquipmentId.empty());
            return "checkout/application";
        }

        try {
            final String mail = userDetails.getUsername();
            this.applyCheckoutService.execute(mail, equipmentList);
            return "redirect:/checkout/application/success";
        } catch (final Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/checkout/application/failed";
        }
    }

    @GetMapping("/success")
    public String success() {
        return "checkout/success_application";
    }

    @GetMapping("/failed")
    public String failed() {
        return "checkout/failed_application";
    }
}
