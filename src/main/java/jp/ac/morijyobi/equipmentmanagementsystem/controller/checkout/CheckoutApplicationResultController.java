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
    ) throws Exception {
        final String mail = getMail(userDetails);

        model.addAttribute(mail);

        return "checkout/application/success";
    }

    @GetMapping("/failed")
    public String failed(
            final @AuthenticationPrincipal UserDetails userDetails,
            final Model model
    ) throws Exception {
        final String mail = getMail(userDetails);

        model.addAttribute(mail);

        return "checkout/application/failed";
    }

    public String getMail(final UserDetails userDetails) throws Exception {
        final String mail = userDetails.getUsername();

        if (mail.isEmpty()) throw new Exception("mail is empty");

        return mail;
    }
}
