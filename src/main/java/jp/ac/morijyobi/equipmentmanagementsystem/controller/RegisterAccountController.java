package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.RegisterAccountForm;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.AccountCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.service.account.IRegisterAccountService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/account/registration")
public class RegisterAccountController {
    private final IRegisterAccountService registerAccountService;

    public RegisterAccountController(final IRegisterAccountService registerAccountService) {
        this.registerAccountService = registerAccountService;
    }

    @GetMapping()
    public String get(
            final @AuthenticationPrincipal UserDetails userDetails,
            final Model model
    ) {
        final String mail = userDetails.getUsername();
        final List<AccountCategory> accountCategoryList = listAccountCategory();
        final RegisterAccountForm registerAccountForm = RegisterAccountForm.empty();

        model.addAttribute(mail);
        model.addAttribute(accountCategoryList);
        model.addAttribute(registerAccountForm);

        return "account/registration/registration";
    }

    @PostMapping(value = "/confirm")
    public String confirm(
            final @AuthenticationPrincipal UserDetails userDetails,
            final @Validated RegisterAccountForm registerAccountForm,
            final BindingResult bindingResult,
            final Model model
    ) {
        final String mail = userDetails.getUsername();
        final List<AccountCategory> accountCategoryList = listAccountCategory();

        model.addAttribute(mail);
        model.addAttribute(accountCategoryList);

        if (bindingResult.hasErrors()) return "account/registration/registration";

        final List<Account> accountList = registerAccountForm.accounts();
        model.addAttribute(accountList);

        return "account/registration/confirm";
    }

    @PostMapping("/submit")
    public String submit(
            final @AuthenticationPrincipal UserDetails userDetails,
            final @Validated RegisterAccountForm registerAccountForm,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            final String mail = userDetails.getUsername();
            final List<AccountCategory> accountCategoryList = listAccountCategory();

            model.addAttribute(mail);
            model.addAttribute(accountCategoryList);

            return "account/registration/registration";
        }

        final int result = 1;//registerAccountService.execute(registerAccountForm);

        if (result == 1) return "redirect:/account/registration?success";

        return "redirect:/account/registration?failed";
    }

    @PostMapping(value = "/submit", params = "cancel")
    public String cancelSubmit(
            final @AuthenticationPrincipal UserDetails userDetails,
            final @Validated RegisterAccountForm registerAccountForm,
            final Model model
    ) {
        final String mail = userDetails.getUsername();
        final List<AccountCategory> accountCategoryList = listAccountCategory();

        model.addAttribute(mail);
        model.addAttribute(accountCategoryList);
        model.addAttribute(registerAccountForm);

        return "account/registration/registration";
    }

    @GetMapping(params = "success")
    public String success() {
        return "account/registration/success";
    }

    @GetMapping(params = "failed")
    public String failed() {
        return "account/registration/failed";
    }

    private List<AccountCategory> listAccountCategory() {
        final AccountCategory[] categories = AccountCategory.values();
        // asList() から返ってくる List は要素数が不変のため new ArrayList() する
        final var categoryList = Arrays.asList(categories);
        final var newCategoryList = new ArrayList<>(categoryList);
        // ここでは学生を取り扱わないため削除しておく
        newCategoryList.remove(AccountCategory.STUDENT);

        return newCategoryList;
    }
}
