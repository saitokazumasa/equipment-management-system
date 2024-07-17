package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/password/change/**")
public class PasswordChangeController {
    @GetMapping()
    public String get() {
        return "password/change/password_change";
    }

    @PostMapping()
    public String post() {
        final boolean result = false;

        if (result) {
            return "password/change/success";
        } else {
            return "password/change/failed";
        }
    }
}
