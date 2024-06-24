package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.service.CheckoutApplicationsService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.EquipmentsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

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
    public String application(Model model) {
        model.addAttribute("message", "備品が選択されていません。");
        return "checkout/application";
    }

    @PostMapping("/application")
    public String application(@RequestParam String[] equipmentIds, RedirectAttributes redirectAttributes) {
        // 備品が選択されていない場合は申請画面に戻る
        if (equipmentIds.length == 0) {
            redirectAttributes.addFlashAttribute("message", "備品が選択されていません。");
            return "redirect:/checkout/application";
        }

        try {
            for (String equipmentId : equipmentIds) {
                int id = Integer.parseInt(equipmentId);

                // TODO: ログイン中のアカウントIDを取得する
                CheckoutApplication checkoutApplication = new CheckoutApplication(-1, id, 1, LocalDateTime.now());
                checkoutApplicationsService.insert(checkoutApplication);
            }
            redirectAttributes.addFlashAttribute("result", "送信しました。");
            redirectAttributes.addFlashAttribute("message", "備品を持ち、管理者に承認をお願いしてください。");
            return "redirect:/checkout/application-result";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("result", "送信時にエラーが発生しました。");
            redirectAttributes.addFlashAttribute("message", "時間をおいて再度 送信してください。\n" +
                    "何度もエラーが出る場合はサポートにお問い合わせください。");
            return "redirect:/checkout/application-result";
        }
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
