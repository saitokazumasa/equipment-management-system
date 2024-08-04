package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterAccountList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account/registration")
public class RegisterAccountController {
    @GetMapping()
    public String get(final Model model) {
        final var registerAccountList = RegisterAccountList.empty();
        model.addAttribute(registerAccountList);
        return "account/registration/registration";
    }

    @PostMapping()
    public String post(
            final @Validated RegisterAccountList registerAccountList,
            final BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) return "account/registration/registration";

        return "account/registration/confirm";
    }

    @PostMapping(params = "submit")
    public String submit(
            final @Validated RegisterAccountList registerAccountList,
            final BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) return "account/registration/registration";

        // TODO: 結果画面へ
        return "account/registration/confirm";
    }
}
