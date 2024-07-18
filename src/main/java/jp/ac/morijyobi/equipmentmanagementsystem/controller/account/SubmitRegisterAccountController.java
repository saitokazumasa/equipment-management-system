package jp.ac.morijyobi.equipmentmanagementsystem.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account/registration/submit")
public class SubmitRegisterAccountController {
    @PostMapping()
    public String post() {
        return "";
    }
}
