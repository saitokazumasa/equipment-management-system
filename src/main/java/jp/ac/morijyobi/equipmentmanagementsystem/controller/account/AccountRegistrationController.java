package jp.ac.morijyobi.equipmentmanagementsystem.controller.account;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.form.TemporaryAccountRegistrationForm;
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
public class AccountRegistrationController {
    @GetMapping()
    public String get(
            final @AuthenticationPrincipal UserDetails userDetails,
            final Model model
    ) {
        final String mail = userDetails.getUsername();
        final TemporaryAccountRegistrationForm temporaryAccountRegistrationForm = TemporaryAccountRegistrationForm.empty();
        final List<AccountCategory> accountCategoryList = listAccountCategory();

        model.addAttribute(mail);
        model.addAttribute(temporaryAccountRegistrationForm);
        model.addAttribute(accountCategoryList);

        return "account/registration/registration";
    }

    @PostMapping()
    public String post(
            final @AuthenticationPrincipal UserDetails userDetails,
            final @Validated TemporaryAccountRegistrationForm temporaryAccountRegistrationForm,
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

        // TODO: 確認画面に飛ばす
        return "account/registration/registration";
    }

    private List<AccountCategory> listAccountCategory() {
        final AccountCategory[] categories = AccountCategory.values();
        // asList() から返ってくる List は要素数が不変のため new ArrayList() する
        final var categoryList = Arrays.asList(categories);
        final var newCategoryList = new ArrayList<AccountCategory>(categoryList);

        // ここでは学生を取り扱わないため削除しておく
        newCategoryList.remove(AccountCategory.STUDENT);

        return newCategoryList;
    }
}
