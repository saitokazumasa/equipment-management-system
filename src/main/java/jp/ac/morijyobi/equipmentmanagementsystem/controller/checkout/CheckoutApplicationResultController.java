package jp.ac.morijyobi.equipmentmanagementsystem.controller.checkout;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout/application")
public class CheckoutApplicationResultController {
    @GetMapping("/success")
    public String success(
            final @AuthenticationPrincipal UserDetails userDetails,
            final Model model
    ) {
        final String mail = userDetails.getUsername();

        model.addAttribute(mail);

        return "checkout/application/success";
    }

    @GetMapping("/failed")
    public String failed(
            final @AuthenticationPrincipal UserDetails userDetails,
            final Model model
    ) {
        final String mail = userDetails.getUsername();

        model.addAttribute(mail);

        return "checkout/application/failed";
    }
}
