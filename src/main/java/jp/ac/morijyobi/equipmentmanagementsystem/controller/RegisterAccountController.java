package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterAccount;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterAccountList;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.AccountCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.service.account.IRegisterAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/account/registration")
public class RegisterAccountController {
    final IRegisterAccountService registerAccountService;
    final String accountCategoriesKey = "accountCategories";
    final String registerAccountKey = "registerAccount";
    final String registerAccountListKey = "registerAccountList";

    public RegisterAccountController(final IRegisterAccountService registerAccountService) {
        this.registerAccountService = registerAccountService;
    }

    @GetMapping()
    public String get(final Model model) {
        // リダイレクトの場合はリセットしない
        final var isRedirected = model.containsAttribute(registerAccountListKey);
        final var registerAccountList = isRedirected ?
                (RegisterAccountList) model.getAttribute(registerAccountListKey) :
                RegisterAccountList.empty();

        model.addAttribute(accountCategoriesKey, accountCategories());
        model.addAttribute(registerAccountKey, RegisterAccount.empty());
        model.addAttribute(registerAccountListKey, registerAccountList);

        return "account/registration/registration";
    }

    @PostMapping(params = "add")
    public String add(
            final @Validated RegisterAccount registerAccount,
            final BindingResult bindingResult,
            final @Validated RegisterAccountList registerAccountList,
            // これがないと初期状態の空リストを通せない
            final BindingResult __,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(accountCategoriesKey, accountCategories());
            return "account/registration/registration";
        }

        // values が null の状態で入ってくることがあるため、その場合は初期化してから追加する
        final RegisterAccountList newRegisterAccountList = registerAccountList.isNull() ?
                RegisterAccountList.empty().add(registerAccount) :
                registerAccountList.add(registerAccount);

        redirectAttributes.addFlashAttribute(registerAccountListKey, newRegisterAccountList);

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

        redirectAttributes.addFlashAttribute(registerAccountListKey, newRegisterAccountList);

        return "redirect:/account/registration";
    }

    @PostMapping(params = "confirm")
    public String confirm(
            final @Validated RegisterAccount registerAccount,
            final BindingResult __,
            final @Validated RegisterAccountList registerAccountList,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(accountCategoriesKey, accountCategories());
            return "account/registration/registration";
        }

        return "account/registration/confirmation";
    }

    @PostMapping(params = "submit")
    public String submit(
            final @Validated RegisterAccount registerAccount,
            final BindingResult __,
            final @Validated RegisterAccountList registerAccountList,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(accountCategoriesKey, accountCategories());
            return "account/registration/registration";
        }

        final boolean result = registerAccountService.execute(registerAccountList);

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

    private List<AccountCategory> accountCategories() {
        final AccountCategory[] accountCategories = AccountCategory.values();

        // ここでは学生アカウントを扱わないため除外する
        final var accountCategoryList = new ArrayList<>(Arrays.asList(accountCategories));
        accountCategoryList.remove(AccountCategory.STUDENT);

        return accountCategoryList;
    }
}
