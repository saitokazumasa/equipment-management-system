package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.ReturnEquipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.ReturnEquipmentList;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.ErrorMessage;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IApplyReturnService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IGetEquipmentService;
import jp.ac.morijyobi.equipmentmanagementsystem.util.IntUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/return/application")
public class ApplyReturnController extends BaseController {
    private final IGetEquipmentService getEquipmentService;
    private final IApplyReturnService applyReturnService;

    private static class AttributeName {
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String RETURN_EQUIPMENT = "returnEquipment";
        public static final String RETURN_EQUIPMENT_LIST = "returnEquipmentList";
    }

    public ApplyReturnController(
            final IGetEquipmentService getEquipmentService,
            final IApplyReturnService applyReturnService
    ) {
        this.getEquipmentService = getEquipmentService;
        this.applyReturnService = applyReturnService;
    }

    @GetMapping()
    public String get(final Model model) {
        // リダイレクトの場合はリセットしない
        final boolean isRedirected = model.containsAttribute(AttributeName.RETURN_EQUIPMENT_LIST);
        final ReturnEquipmentList returnEquipmentList = isRedirected ?
                (ReturnEquipmentList) model.getAttribute(AttributeName.RETURN_EQUIPMENT_LIST) :
                ReturnEquipmentList.empty();

        model.addAttribute(AttributeName.RETURN_EQUIPMENT, ReturnEquipment.empty());
        model.addAttribute(AttributeName.RETURN_EQUIPMENT_LIST, returnEquipmentList);
        return "return/application";
    }

    @PostMapping(params = "add")
    public String add(
            final @Validated ReturnEquipment returnEquipment,
            final BindingResult bindingResult,
            final @Validated ReturnEquipmentList returnEquipmentList,
            // これがないと初期状態の空リストを通せない
            final BindingResult __,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) return "return/application";

        final int equipmentId = IntUtil.TryToInt(returnEquipment.getEquipmentId());
        final Equipment equipment = this.getEquipmentService.executeOnLoanById(equipmentId);
        if (equipment == null) {
            model.addAttribute(AttributeName.ERROR_MESSAGE, ErrorMessage.NOT_EXIST_VALUE.getText());
            return "return/application";
        }

        returnEquipment.setEquipment(equipment);

        // values が null の状態で入ってくることがあるため、その場合は初期化してから追加する
        final ReturnEquipmentList newReturnEquipmentList = returnEquipmentList.isNull() ?
                ReturnEquipmentList.empty().add(returnEquipment) :
                returnEquipmentList.add(returnEquipment);

        redirectAttributes.addFlashAttribute(AttributeName.RETURN_EQUIPMENT_LIST, newReturnEquipmentList);
        return "redirect:/return/application";
    }

    @PostMapping(params = "remove")
    public String remove(
            final @Validated ReturnEquipment returnEquipment,
            final BindingResult _1,
            final @Validated ReturnEquipmentList returnEquipmentList,
            final BindingResult _2,
            final HttpServletRequest request,
            final RedirectAttributes redirectAttributes
    ) {
        final int index = Integer.parseInt(request.getParameter("remove"));
        final ReturnEquipmentList newReturnEquipmentList = returnEquipmentList.remove(index);

        redirectAttributes.addFlashAttribute(AttributeName.RETURN_EQUIPMENT_LIST, newReturnEquipmentList);
        return "redirect:/return/application";
    }

    @PostMapping(params = "submit")
    public String submit(
            final @Validated ReturnEquipmentList returnEquipmentList,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AttributeName.RETURN_EQUIPMENT, ReturnEquipment.empty());
            return "return/application";
        }

        try {
            this.applyReturnService.execute(returnEquipmentList);
            return "redirect:/return/application/success";
        } catch (final Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/return/application/failed";
        }
    }

    @GetMapping("/success")
    public String success() {
        return "return/success_application";
    }

    @GetMapping("/failed")
    public String failed() {
        return "return/failed_application";
    }
}
