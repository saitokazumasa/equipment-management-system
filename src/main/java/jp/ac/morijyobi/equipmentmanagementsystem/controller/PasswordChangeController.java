package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/password/change")
public class PasswordChangeController {
    @GetMapping()
    public String get() {
        return "password/change/password_change";
    }
}
