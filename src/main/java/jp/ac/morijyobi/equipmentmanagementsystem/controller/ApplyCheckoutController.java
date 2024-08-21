package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.CheckoutEquipmentId;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.CheckoutEquipmentList;
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
public class ApplyCheckoutController extends BaseController {
    private final IGetEquipmentService getEquipmentService;
    private final IApplyCheckoutService applyCheckoutService;

    private static class AttributeName {
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String CHECKOUT_EQUIPMENT_ID = "checkoutEquipmentId";
        public static final String CHECKOUT_EQUIPMENT_LIST = "checkoutEquipmentList";
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
        final boolean isRedirected = model.containsAttribute(AttributeName.CHECKOUT_EQUIPMENT_LIST);
        final CheckoutEquipmentList checkoutEquipmentList = isRedirected ?
                (CheckoutEquipmentList) model.getAttribute(AttributeName.CHECKOUT_EQUIPMENT_LIST) :
                CheckoutEquipmentList.empty();

        model.addAttribute(AttributeName.CHECKOUT_EQUIPMENT_ID, CheckoutEquipmentId.empty());
        model.addAttribute(AttributeName.CHECKOUT_EQUIPMENT_LIST, checkoutEquipmentList);
        return "checkout/application";
    }

    @PostMapping(params = "add")
    public String add(
            final @Validated CheckoutEquipmentId checkoutEquipmentId,
            final BindingResult bindingResult,
            final @Validated CheckoutEquipmentList checkoutEquipmentList,
            // これがないと初期状態の空リストを通せない
            final BindingResult __,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) return "checkout/application";

        final Equipment equipment = this.getEquipmentService.executeAvailableForLoanById(checkoutEquipmentId.toInt());
        if (equipment == null) {
            model.addAttribute(AttributeName.ERROR_MESSAGE, ErrorMessage.NOT_EXIST_VALUE.getText());
            return "checkout/application";
        }

        // values が null の状態で入ってくることがあるため、その場合は初期化してから追加する
        final CheckoutEquipmentList newCheckoutEquipmentList = checkoutEquipmentList.isNull() ?
                CheckoutEquipmentList.empty().add(equipment) :
                checkoutEquipmentList.add(equipment);

        redirectAttributes.addFlashAttribute(AttributeName.CHECKOUT_EQUIPMENT_LIST, newCheckoutEquipmentList);
        return "redirect:/checkout/application";
    }

    @PostMapping(params = "remove")
    public String remove(
            final @Validated CheckoutEquipmentId checkoutEquipmentId,
            final BindingResult _1,
            final @Validated CheckoutEquipmentList checkoutEquipmentList,
            final BindingResult _2,
            final HttpServletRequest request,
            final RedirectAttributes redirectAttributes
    ) {
        final int index = Integer.parseInt(request.getParameter("remove"));
        final CheckoutEquipmentList newCheckoutEquipmentList = checkoutEquipmentList.remove(index);

        redirectAttributes.addFlashAttribute(AttributeName.CHECKOUT_EQUIPMENT_LIST, newCheckoutEquipmentList);
        return "redirect:/checkout/application";
    }

    @PostMapping(params = "submit")
    public String submit(
            final @AuthenticationPrincipal UserDetails userDetails,
            final @Validated CheckoutEquipmentList checkoutEquipmentList,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AttributeName.CHECKOUT_EQUIPMENT_ID, CheckoutEquipmentId.empty());
            return "checkout/application";
        }

        try {
            final String mail = userDetails.getUsername();
            this.applyCheckoutService.execute(mail, checkoutEquipmentList);
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
