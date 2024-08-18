package jp.ac.morijyobi.equipmentmanagementsystem.controller.equipment;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.ListEquipmentForm;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.IListEquipmentService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.implement.FetchEquipmentService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipmentCategory.IListEquipmentCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/equipment/list")
public class EquipmentListController {
    private final IListEquipmentService listEquipmentService;
    private final IListEquipmentCategoryService listEquipmentCategoryService;
    private final FetchEquipmentService fetchEquipmentService;

    public EquipmentListController(IListEquipmentService listEquipmentService, IListEquipmentCategoryService listEquipmentCategoryService, FetchEquipmentService fetchEquipmentService) {
        this.listEquipmentService = listEquipmentService;
        this.listEquipmentCategoryService = listEquipmentCategoryService;
        this.fetchEquipmentService = fetchEquipmentService;
    }

    @GetMapping()
    public String list(Model model) {
        final ListEquipmentForm form = new ListEquipmentForm();
        final List<Equipment> equipments = listEquipmentService.execute();
        final List<EquipmentCategory> categories = listEquipmentCategoryService.execute();
        final List<EquipmentState> states = List.of(EquipmentState.values());

        model.addAttribute("form", form);
        model.addAttribute("equipments",equipments);
        model.addAttribute("categories",categories);
        model.addAttribute("states",states);
        return "equipment/list/list";
    }

    @PostMapping()
    public String list(ListEquipmentForm form, Model model) {
        final List<Equipment> equipments = fetchEquipmentService.executeByUserInput(form.getName(), form.getCategoryIdList(), form.getStateList());
        final List<EquipmentCategory> categories = listEquipmentCategoryService.execute();
        final List<EquipmentState> states = List.of(EquipmentState.values());

        model.addAttribute("form", form);
        model.addAttribute("equipments",equipments);
        model.addAttribute("categories",categories);
        model.addAttribute("states",states);
        return "equipment/list/list";
    }
}