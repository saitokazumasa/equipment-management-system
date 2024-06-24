package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.service.CheckoutApplicationsService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.EquipmentsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    private final EquipmentsService equipmentsService;
    private final CheckoutApplicationsService checkoutApplicationsService;

    public CheckoutController(EquipmentsService equipmentsService, CheckoutApplicationsService checkoutApplicationsService) {
        this.equipmentsService = equipmentsService;
        this.checkoutApplicationsService = checkoutApplicationsService;
    }

    @GetMapping("/application")
    public String application() {
        return "checkout/application";
    }

    @PostMapping("/application")
    public String application(@RequestParam String[] equipmentIds) {
        for (String equipmentId : equipmentIds) {
            int id = Integer.parseInt(equipmentId);
            CheckoutApplication checkoutApplication = new CheckoutApplication(-1, id, 1, LocalDateTime.now());
            checkoutApplicationsService.insert(checkoutApplication);
        }
        return "redirect:/checkout/application-result";
    }

    // 対象の備品の情報を返す
    @GetMapping("/application/addList")
    @ResponseBody
    public Equipment addList(@RequestParam int equipmentId) {
        return equipmentsService.selectById(equipmentId);
    }

    @GetMapping("/application-result")
    public String applicationResult() {
        return "checkout/application-result";
    }
}
