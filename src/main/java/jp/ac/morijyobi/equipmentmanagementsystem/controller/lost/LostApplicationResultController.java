package jp.ac.morijyobi.equipmentmanagementsystem.controller.lost;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lost/application")
public class LostApplicationResultController {
    @GetMapping("/success")
    public String success(Model model) {
        model.addAttribute("result", "送信しました");
        model.addAttribute("message", "管理者に承認をお願いしてください。");
        return "lost/application/result";
    }

    @GetMapping("/failed")
    public String failed(Model model) {
        model.addAttribute("result", "送信時にエラーが発生しました。");
        model.addAttribute("message", "時間をおいて再度 送信してください。\n" +
                "何度もエラーが出る場合はサポートにお問い合わせください。");
        return "lost/application/result";
    }



}
