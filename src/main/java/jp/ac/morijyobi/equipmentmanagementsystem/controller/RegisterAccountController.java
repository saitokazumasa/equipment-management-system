package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterAccount;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterAccountList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/account/registration")
public class RegisterAccountController {
    @GetMapping()
    public String get(final Model model) {
        // リダイレクトしてきた場合は registerAccount のみ初期化
        final boolean isRedirect = model.containsAttribute("registerAccountList");
        if (isRedirect) {
            final RegisterAccount registerAccount = RegisterAccount.empty();
            model.addAttribute("registerAccount", registerAccount);
            return "account/registration/registration";
        }

        final RegisterAccount registerAccount = RegisterAccount.empty();
        final RegisterAccountList registerAccountList = RegisterAccountList.empty();

        model.addAttribute("registerAccount", registerAccount);
        model.addAttribute("registerAccountList", registerAccountList);

        return "account/registration/registration";
    }

    @PostMapping(params = "add")
    public String add(
            final @Validated RegisterAccount registerAccount,
            final BindingResult registerAccountBindingResult,
            final @Validated RegisterAccountList registerAccountList,
            // NOTE: これがないと初期状態の空リストを通せない
            final BindingResult __,
            final RedirectAttributes redirectAttributes
    ) {
        if (registerAccountBindingResult.hasErrors()) return "account/registration/registration";

        final RegisterAccountList newRegisterAccountList = registerAccountList.isEmpty() ?
                RegisterAccountList.empty().add(registerAccount) :
                registerAccountList.add(registerAccount);

        redirectAttributes.addFlashAttribute("registerAccountList", newRegisterAccountList);

        return "redirect:/account/registration";
    }

    @PostMapping(params = "remove")
    public String remove(
            final @Validated RegisterAccount registerAccount,
            final BindingResult _1,
            final @Validated RegisterAccountList registerAccountList,
            final BindingResult _2,
            final HttpServletRequest request,
            final RedirectAttributes redirectAttributes
    ) {
        final int index = Integer.parseInt(request.getParameter("remove"));
        final RegisterAccountList newRegisterAccountList = registerAccountList.remove(index);

        redirectAttributes.addFlashAttribute("registerAccountList", newRegisterAccountList);

        return "redirect:/account/registration";
    }

    @PostMapping(params = "confirm")
    public String confirm(
            final @Validated RegisterAccount registerAccount,
            final BindingResult __,
            final @Validated RegisterAccountList registerAccountList,
            final BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) return "account/registration/registration";

        return "account/registration/confirmation";
    }

    @PostMapping(params = "submit")
    public String submit(
            final @Validated RegisterAccount registerAccount,
            final BindingResult __,
            final @Validated RegisterAccountList registerAccountList,
            final BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) return "account/registration/registration";

        // TODO: 登録し、結果を取得する

        final boolean result = true;

        if (result) return "redirect:/account/registration/success";

        return "redirect:/account/registration/failed";
    }

    @GetMapping("success")
    public String success() {
        return "account/registration/success";
    }

    @GetMapping("failed")
    public String failed() {
        return "account/registration/failed";
    }
}
