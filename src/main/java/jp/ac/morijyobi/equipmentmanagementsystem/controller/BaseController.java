package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BaseController {
    @ModelAttribute("mail")
    public void getMail(
            final @AuthenticationPrincipal UserDetails userDetails,
            final Model model
    ) {
        if (userDetails == null) return;

        model.addAttribute("mail", userDetails.getUsername());
    }
}
