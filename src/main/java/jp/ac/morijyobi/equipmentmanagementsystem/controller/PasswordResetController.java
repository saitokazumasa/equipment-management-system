package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/password_reset")
public class PasswordResetController {
    @GetMapping()
    public String get() {
        return "password_reset";
    }
}
