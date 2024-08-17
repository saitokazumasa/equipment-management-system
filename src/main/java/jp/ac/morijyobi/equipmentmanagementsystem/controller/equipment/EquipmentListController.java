package jp.ac.morijyobi.equipmentmanagementsystem.controller.equipment;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.ListEquipmentForm;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.IListEquipmentService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipmentCategory.IListEquipmentCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/equipment/list")
public class EquipmentListController {
    private final IListEquipmentService listEquipmentService;
    private final IListEquipmentCategoryService listEquipmentCategoryService;

    public EquipmentListController(IListEquipmentService listEquipmentService, IListEquipmentCategoryService listEquipmentCategoryService) {
        this.listEquipmentService = listEquipmentService;
        this.listEquipmentCategoryService = listEquipmentCategoryService;
    }

    @GetMapping()
    public String list(Model model) {
        final ListEquipmentForm form = new ListEquipmentForm();
        final List<Equipment> equipments = listEquipmentService.execute();
        final List<EquipmentCategory> categories = listEquipmentCategoryService.execute();

        model.addAttribute("form", form);
        model.addAttribute("equipments",equipments);
        model.addAttribute("categories",categories);
        return "equipment/list/list";
    }
}