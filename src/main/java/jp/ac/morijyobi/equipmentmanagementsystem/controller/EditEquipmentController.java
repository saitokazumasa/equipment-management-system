package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.EditEquipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.StorageLocation;
import jp.ac.morijyobi.equipmentmanagementsystem.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/equipment/edit")
public class EditEquipmentController extends BaseController {
    private final IListEquipmentCategoryService listEquipmentCategoryService;
    private final IListStorageLocationService listStorageLocationService;
    private final IGetEquipmentService getEquipmentService;
    private final IGetEquipmentCategoryService getEquipmentCategoryService;
    private final IGetStorageLocationService getStorageLocationService;

    public EditEquipmentController(IListEquipmentCategoryService listEquipmentCategoryService, IListStorageLocationService listStorageLocationService, IGetEquipmentService getEquipmentService, IGetEquipmentCategoryService getEquipmentCategoryService, IGetStorageLocationService getStorageLocationService) {
        this.listEquipmentCategoryService = listEquipmentCategoryService;
        this.listStorageLocationService = listStorageLocationService;
        this.getEquipmentService = getEquipmentService;
        this.getEquipmentCategoryService = getEquipmentCategoryService;
        this.getStorageLocationService = getStorageLocationService;
    }

    private static class AttributeName {
        public static String EQUIPMENT_CATEGORY_LIST = "equipmentCategoryList";
        public static String STORAGE_LOCATION_LIST = "storageLocationList";
        public static String EDIT_EQUIPMENT = "editEquipment";
        public static String EQUIPMENT_ID = "equipmentId";
    }

    @GetMapping()
    public String get(@RequestParam final int equipmentId,
                      final Model model) {
        if (equipmentId < 1) {
            return "redirect:/equipment/list";
        }

        final Equipment beforeEquipment = getEquipmentService.executeById(equipmentId);

        if (beforeEquipment == null) {
            return "redirect:/equipment/list";
        }

        final StorageLocation storageLocation = getStorageLocationService.executeById(beforeEquipment.getStorageLocationId());
        final EquipmentCategory equipmentCategory = getEquipmentCategoryService.executeById(beforeEquipment.getCategoryId());

        model.addAttribute(AttributeName.EQUIPMENT_CATEGORY_LIST, listEquipmentCategoryService.execute());
        model.addAttribute(AttributeName.STORAGE_LOCATION_LIST, listStorageLocationService.execute());
        model.addAttribute(AttributeName.EDIT_EQUIPMENT, EditEquipment.set(beforeEquipment, storageLocation, equipmentCategory));

        return "equipment/edit";
    }

    @PostMapping(params = "confirm")
    public String confirm(final @Validated EditEquipment EditEquipment,
                          final BindingResult bindingResult,
                          final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AttributeName.EQUIPMENT_CATEGORY_LIST, listEquipmentCategoryService.execute());
            model.addAttribute(AttributeName.STORAGE_LOCATION_LIST, listStorageLocationService.execute());
            return "equipment/edit";
        }

        return "equipment/confirm_edit";
    }

    @PostMapping(params = "submit")
    public String submit(final @Validated EditEquipment EditEquipment,
                         final BindingResult bindingResult,
                         final Model model) {
        if (bindingResult.hasErrors()) {
            return "equipment/edit";
        }
        try {
            return "redirect:/equipment/edit/success";
        } catch (final Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/equipment/edit/failed";
        }
    }

    @PostMapping(params = "cancel")
    public String cancel(
            final @Validated EditEquipment EditEquipment,
            final @RequestParam int equipmentId,
            final Model model) {
        model.addAttribute(AttributeName.EQUIPMENT_CATEGORY_LIST, listEquipmentCategoryService.execute());
        model.addAttribute(AttributeName.STORAGE_LOCATION_LIST, listStorageLocationService.execute());
        model.addAttribute(AttributeName.EQUIPMENT_ID, equipmentId);
        return "equipment/edit";
    }

    @GetMapping("/success")
    public String success() {
        return "equipment/success_edit";
    }

    @GetMapping("/failed")
    public String failed() {
        return "equipment/failed_edit";
    }
}