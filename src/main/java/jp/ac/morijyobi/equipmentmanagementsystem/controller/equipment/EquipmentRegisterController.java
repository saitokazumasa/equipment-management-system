package jp.ac.morijyobi.equipmentmanagementsystem.controller.equipment;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.equipmentRegisterForm;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipment.implement.RegisterEquipmentService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.equipmentCategory.IListEquipmentCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/equipment/register")
public class EquipmentRegisterController {
    private final IListEquipmentCategoryService listEquipmentCategoryService;
    private final RegisterEquipmentService registerEquipmentService;

    public EquipmentRegisterController(IListEquipmentCategoryService listEquipmentCategoryService, RegisterEquipmentService registerEquipmentService) {
        this.listEquipmentCategoryService = listEquipmentCategoryService;
        this.registerEquipmentService = registerEquipmentService;
    }


    @GetMapping()
    public String register(Model model) {
        final equipmentRegisterForm equipmentRegisterForm = new equipmentRegisterForm();

        final List<EquipmentCategory> equipmentCategories = listEquipmentCategoryService.execute();

        model.addAttribute("form", equipmentRegisterForm);
        model.addAttribute("categories",equipmentCategories);

        return "equipment/register/register";
    }

    @PostMapping("/confirm")
    public String registerExec(@Validated equipmentRegisterForm equipmentRegisterForm,
                               BindingResult bindingResult,
                               Model model) {
        final List<EquipmentCategory> equipmentCategories = listEquipmentCategoryService.execute();
        model.addAttribute("form", equipmentRegisterForm);
        model.addAttribute("categories", equipmentCategories);

        if (bindingResult.hasErrors()) {
            return "equipment/register/register";
        }

        return "equipment/register/confirm";
    }

    @PostMapping(value = "", params = "complete")
    public String registerComplete(@Validated equipmentRegisterForm equipmentRegisterForm,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message","登録に失敗しました。");
        } else {
            int a = registerEquipmentService.execute(equipmentRegisterForm);
            System.out.println(a);
            redirectAttributes.addFlashAttribute("message","登録しました。");
        }
        return "redirect:/equipment/list";
    }

    @PostMapping(value = "", params = "edit")
    public String registerEdit(@Validated equipmentRegisterForm equipmentRegisterForm,
                               Model model) {
        final List<EquipmentCategory> equipmentCategories = listEquipmentCategoryService.execute();
        model.addAttribute("categories", equipmentCategories);
        return "equipment/register/register";
    }

}
