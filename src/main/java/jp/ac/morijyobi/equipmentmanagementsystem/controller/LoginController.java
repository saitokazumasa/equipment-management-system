package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public String get(final Model model) {
        model.addAttribute("mail", "sample@mail.com");
        return "login";
    }
}
