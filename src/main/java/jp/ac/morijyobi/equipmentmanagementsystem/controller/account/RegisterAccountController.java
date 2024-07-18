package jp.ac.morijyobi.equipmentmanagementsystem.controller.account;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.TemporaryAccount;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.RegisterTemporaryAccountForm;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.AccountCategory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/account/registration")
public class RegisterAccountController {
    @GetMapping()
    public String get(
            final @AuthenticationPrincipal UserDetails userDetails,
            final Model model
    ) {
        final String mail = userDetails.getUsername();
        final List<AccountCategory> accountCategoryList = listAccountCategory();
        final RegisterTemporaryAccountForm registerTemporaryAccountForm = RegisterTemporaryAccountForm.empty();

        model.addAttribute(mail);
        model.addAttribute(accountCategoryList);
        model.addAttribute(registerTemporaryAccountForm);

        return "account/registration/registration";
    }

    @PostMapping()
    public String post(
            final @AuthenticationPrincipal UserDetails userDetails,
            final @Validated RegisterTemporaryAccountForm registerTemporaryAccountForm,
            final BindingResult bindingResult,
            final Model model
    ) {
        final String mail = userDetails.getUsername();
        final List<AccountCategory> accountCategoryList = listAccountCategory();

        model.addAttribute(mail);
        model.addAttribute(accountCategoryList);

        if (bindingResult.hasErrors()) return "account/registration/registration";

        final TemporaryAccount[] temporaryAccounts = registerTemporaryAccountForm.temporaryAccounts();
        final List<TemporaryAccount> temporaryAccountList = Arrays.asList(temporaryAccounts);
        model.addAttribute(temporaryAccountList);

        return "account/registration/confirm";
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
