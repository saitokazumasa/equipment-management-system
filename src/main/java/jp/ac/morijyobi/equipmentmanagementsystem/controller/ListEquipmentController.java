package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.EquipmentSearchCriteria;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IListEquipmentService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IListEquipmentCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/equipment/list")
public class ListEquipmentController extends BaseController {
    private final IListEquipmentCategoryService listEquipmentCategoryService;
    private final IListEquipmentService listEquipmentService;

    private static class AttributeName {
        public static String EQUIPMENT_STATE_LIST = "equipmentStateList";
        public static String EQUIPMENT_CATEGORY_LIST = "equipmentCategoryList";
        public static String EQUIPMENT_LIST = "equipmentList";
        public static String EQUIPMENT_SEARCH_CRITERIA = "equipmentSearchCriteria";
    }

    public ListEquipmentController(
            final IListEquipmentCategoryService listEquipmentCategoryService,
            final IListEquipmentService listEquipmentService
    ) {
        this.listEquipmentCategoryService = listEquipmentCategoryService;
        this.listEquipmentService = listEquipmentService;
    }

    @GetMapping()
    public String get(final Model model) {
        final List<EquipmentState> states = equipmentStateList();
        final List<Integer> categoryIds = listEquipmentCategoryService.ids();
        final EquipmentSearchCriteria searchCriteria = EquipmentSearchCriteria.generate(categoryIds, states);

        model.addAttribute(AttributeName.EQUIPMENT_STATE_LIST, states);
        model.addAttribute(AttributeName.EQUIPMENT_CATEGORY_LIST, listEquipmentCategoryService.execute());
        model.addAttribute(AttributeName.EQUIPMENT_LIST, listEquipmentService.execute());
        model.addAttribute(AttributeName.EQUIPMENT_SEARCH_CRITERIA, searchCriteria);
        return "equipment/list";
    }

    @PostMapping()
    public String post(
            final @Validated EquipmentSearchCriteria equipmentSearchCriteria,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AttributeName.EQUIPMENT_STATE_LIST, equipmentStateList());
            model.addAttribute(AttributeName.EQUIPMENT_CATEGORY_LIST, listEquipmentCategoryService.execute());
            model.addAttribute(AttributeName.EQUIPMENT_LIST, listEquipmentService.execute());
            return "equipment/list";
        }

        final List<Equipment> equipments = listEquipmentService.search(equipmentSearchCriteria);

        model.addAttribute(AttributeName.EQUIPMENT_STATE_LIST, equipmentStateList());
        model.addAttribute(AttributeName.EQUIPMENT_CATEGORY_LIST, listEquipmentCategoryService.execute());
        model.addAttribute(AttributeName.EQUIPMENT_LIST, equipments);
        return "equipment/list";
    }

    private List<EquipmentState> equipmentStateList() {
        return Arrays.asList(EquipmentState.values());
    }
}