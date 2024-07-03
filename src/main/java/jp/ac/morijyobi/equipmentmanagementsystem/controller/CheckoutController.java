package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.CheckoutApplication;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.service.ICheckoutApplicationsService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IEquipmentsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    private final IEquipmentsService equipmentsService;
    private final ICheckoutApplicationsService checkoutApplicationsService;

    public CheckoutController(final IEquipmentsService equipmentsService, final ICheckoutApplicationsService checkoutApplicationsService) {
        this.equipmentsService = equipmentsService;
        this.checkoutApplicationsService = checkoutApplicationsService;
    }

    @GetMapping("/application")
    public String application(final Model model) {
        model.addAttribute("equipmentList", equipmentsService.getAll());
        return "checkout/application";
    }

    @PostMapping("/application")
    public String application(final @RequestParam String[] idList, final RedirectAttributes redirectAttributes) {
        // 備品が選択されていない場合は申請画面に戻る
        if (idList.length == 0) {
            return "redirect:/checkout/application";
        }

        try {
            for (String equipmentId : idList) {
                int id = Integer.parseInt(equipmentId);

                // TODO: ログイン中のアカウントIDを取得する
                CheckoutApplication checkoutApplication = new CheckoutApplication(-1, id, 1, LocalDateTime.now());
                checkoutApplicationsService.execute(checkoutApplication);
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

    @GetMapping("/application-result")
    public String applicationResult() {
        return "checkout/application-result";
    }
}
