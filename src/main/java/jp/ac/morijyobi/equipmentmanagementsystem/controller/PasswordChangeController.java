package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.output.ErrorMessage;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.output.SuccessMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/password_change")
public class PasswordChangeController {
    @GetMapping()
    public String get() {
        return "password_change";
    }

    @PostMapping()
    public String post(final RedirectAttributes redirectAttributes) {
        final boolean result = true;

        if (result) {
            final var message = new SuccessMessage("送信しました。");
            redirectAttributes.addFlashAttribute(message);
            return "redirect:/マイアカウント詳細画面"; // マイアカウント詳細画面に遷移する
        }

        final var message = new ErrorMessage("送信時にエラーが発生しました。");
        redirectAttributes.addFlashAttribute(message);
        return "redirect:/マイアカウント詳細画面"; // マイアカウント詳細画面に遷移する
    }
}
