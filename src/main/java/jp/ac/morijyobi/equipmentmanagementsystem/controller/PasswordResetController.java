package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.output.ErrorMessage;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.output.SuccessMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/password_reset")
public class PasswordResetController {
    @GetMapping()
    public String get() {
        return "password_reset";
    }

    @PostMapping()
    public String post(final RedirectAttributes redirectAttributes) {
        // TODO: パスワードリセットのURLを送信
        // TODO: 送信結果を取得
        final boolean result = true;

        if (result) {
            final var message = new SuccessMessage("送信しました。");
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/password_reset";
        }

        final var message = new ErrorMessage("送信時にエラーが発生しました。");
        redirectAttributes.addFlashAttribute(message);
        return "redirect:/password_reset";
    }
}
