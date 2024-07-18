package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.output.ErrorMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping()
    public String get() {
        return "login";
    }

    @GetMapping(params = "error")
    public String error(final Model model) {
        final var errorMessage = new ErrorMessage("ログインに失敗しました。");
        model.addAttribute("message", errorMessage);
        return "login";
    }
}
