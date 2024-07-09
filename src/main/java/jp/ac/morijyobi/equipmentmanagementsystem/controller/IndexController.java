package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/index"})
public class IndexController {

    @GetMapping()
    public String get() {
        //TODO: 後ほど返却画面に返る
        return "redirect:/login";
    }
}
