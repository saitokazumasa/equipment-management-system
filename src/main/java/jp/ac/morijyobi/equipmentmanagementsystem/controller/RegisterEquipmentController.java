package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.*;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IRegisterEquipmentService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IListEquipmentCategoryService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IListStorageLocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/equipment/registration")
public class RegisterEquipmentController extends BaseController {
    private final IListEquipmentCategoryService listEquipmentCategoryService;
    private final IListStorageLocationService listStorageLocationService;
    private final IRegisterEquipmentService registerEquipmentService;

    private static class AttributeName {
        public static String EQUIPMENT_CATEGORY_LIST = "equipmentCategoryList";
        public static String STORAGE_LOCATION_LIST = "storageLocationList";
        public static String REGISTER_EQUIPMENT = "registerEquipment";
        public static String REGISTER_EQUIPMENT_LIST = "registerEquipmentList";
    }

    public RegisterEquipmentController(
            final IListEquipmentCategoryService listEquipmentCategoryService,
            final IListStorageLocationService listStorageLocationService,
            final IRegisterEquipmentService registerEquipmentService
    ) {
        this.listEquipmentCategoryService = listEquipmentCategoryService;
        this.listStorageLocationService = listStorageLocationService;
        this.registerEquipmentService = registerEquipmentService;
    }

    @GetMapping()
    public String get(final Model model) {
        // リダイレクトの場合はリセットしない
        final boolean isRedirected = model.containsAttribute(AttributeName.REGISTER_EQUIPMENT_LIST);
        final RegisterEquipmentList registerEquipmentList = isRedirected ?
                (RegisterEquipmentList) model.getAttribute(AttributeName.REGISTER_EQUIPMENT_LIST) :
                RegisterEquipmentList.empty();

        model.addAttribute(AttributeName.EQUIPMENT_CATEGORY_LIST, listEquipmentCategoryService.execute());
        model.addAttribute(AttributeName.STORAGE_LOCATION_LIST, listStorageLocationService.execute());
        model.addAttribute(AttributeName.REGISTER_EQUIPMENT, RegisterEquipment.empty());
        model.addAttribute(AttributeName.REGISTER_EQUIPMENT_LIST, registerEquipmentList);
        return "equipment/registration";
    }

    @PostMapping(params = "add")
    public String add(
            final @Validated RegisterEquipment registerEquipment,
            final BindingResult bindingResult,
            final @Validated RegisterEquipmentList registerEquipmentList,
            // これがないと初期状態の空リストを通せない
            final BindingResult __,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AttributeName.EQUIPMENT_CATEGORY_LIST, listEquipmentCategoryService.execute());
            model.addAttribute(AttributeName.STORAGE_LOCATION_LIST, listStorageLocationService.execute());
            return "equipment/registration";
        }

        // values が null の状態で入ってくることがあるため、その場合は初期化してから追加する
        final RegisterEquipmentList newRegisterEquipmentList = registerEquipmentList.isNull() ?
                RegisterEquipmentList.empty().add(registerEquipment) :
                registerEquipmentList.add(registerEquipment);

        redirectAttributes.addFlashAttribute(AttributeName.REGISTER_EQUIPMENT_LIST, newRegisterEquipmentList);
        return "redirect:/equipment/registration";
    }

    @PostMapping(params = "remove")
    public String remove(
            final @Validated RegisterEquipment registerEquipment,
            final BindingResult _1,
            final @Validated RegisterEquipmentList registerEquipmentList,
            final BindingResult _2,
            final HttpServletRequest request,
            final RedirectAttributes redirectAttributes
    ) {
        final int index = Integer.parseInt(request.getParameter("remove"));
        final RegisterEquipmentList newRegisterEquipmentList = registerEquipmentList.remove(index);

        redirectAttributes.addFlashAttribute(AttributeName.REGISTER_EQUIPMENT_LIST, newRegisterEquipmentList);
        return "redirect:/equipment/registration";
    }

    @PostMapping(params = "confirm")
    public String confirm(
            final @Validated RegisterEquipmentList registerEquipmentList,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AttributeName.EQUIPMENT_CATEGORY_LIST, listEquipmentCategoryService.execute());
            model.addAttribute(AttributeName.STORAGE_LOCATION_LIST, listStorageLocationService.execute());
            model.addAttribute(AttributeName.REGISTER_EQUIPMENT, RegisterEquipment.empty());
            return "equipment/registration";
        }
        return "equipment/confirm_registration";
    }

    @PostMapping(params = "submit")
    public String submit(
            final @Validated RegisterEquipmentList registerEquipmentList,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AttributeName.EQUIPMENT_CATEGORY_LIST, listEquipmentCategoryService.execute());
            model.addAttribute(AttributeName.STORAGE_LOCATION_LIST, listStorageLocationService.execute());
            model.addAttribute(AttributeName.REGISTER_EQUIPMENT, RegisterEquipment.empty());
            return "equipment/registration";
        }

        try {
            registerEquipmentService.execute(registerEquipmentList);
            return "redirect:/equipment/registration/success";
        } catch (final Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/equipment/registration/failed";
        }
    }

    @PostMapping(params = "cancel")
    public String cancel(
            final @Validated RegisterEquipmentList registerEquipmentList,
            final BindingResult __,
            final RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute(AttributeName.REGISTER_EQUIPMENT_LIST, registerEquipmentList);
        return "redirect:/equipment/registration";
    }

    @GetMapping("/success")
    public String success() {
        return "equipment/success_registration";
    }

    @GetMapping("/failed")
    public String failed() {
        return "equipment/failed_registration";
    }
}
