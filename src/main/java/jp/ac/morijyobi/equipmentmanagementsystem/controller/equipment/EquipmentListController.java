package jp.ac.morijyobi.equipmentmanagementsystem.controller.equipment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/equipment/list")
public class EquipmentListController {
    @GetMapping()
    public String list() {
        return "equipment/list/list";
    }
}
