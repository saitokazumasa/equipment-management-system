package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterAccount;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterAccountList;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.AccountCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IRegisterAccountService;
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
public class RegisterAccountController extends BaseController {
    private final IRegisterAccountService registerAccountService;

    private static class AttributeName {
        public static final String ACCOUNT_CATEGORY_LIST = "accountCategoryList";
        public static final String REGISTER_ACCOUNT = "registerAccount";
        public static final String REGISTER_ACCOUNT_LIST = "registerAccountList";
    }

    public RegisterAccountController(final IRegisterAccountService registerAccountService) {
        this.registerAccountService = registerAccountService;
    }

    @GetMapping()
    public String get(final Model model) {
        // リダイレクトの場合はリセットしない
        final boolean isRedirected = model.containsAttribute(AttributeName.REGISTER_ACCOUNT_LIST);
        final RegisterAccountList registerAccountList = isRedirected ?
                (RegisterAccountList) model.getAttribute(AttributeName.REGISTER_ACCOUNT_LIST) :
                RegisterAccountList.empty();

        model.addAttribute(AttributeName.ACCOUNT_CATEGORY_LIST, accountCategories());
        model.addAttribute(AttributeName.REGISTER_ACCOUNT, RegisterAccount.empty());
        model.addAttribute(AttributeName.REGISTER_ACCOUNT_LIST, registerAccountList);
        return "account/registration";
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
            model.addAttribute(AttributeName.ACCOUNT_CATEGORY_LIST, accountCategories());
            return "account/registration";
        }

        // values が null の状態で入ってくることがあるため、その場合は初期化してから追加する
        final RegisterAccountList newRegisterAccountList = registerAccountList.isNull() ?
                RegisterAccountList.empty().add(registerAccount) :
                registerAccountList.add(registerAccount);

        redirectAttributes.addFlashAttribute(AttributeName.REGISTER_ACCOUNT_LIST, newRegisterAccountList);
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

        redirectAttributes.addFlashAttribute(AttributeName.REGISTER_ACCOUNT_LIST, newRegisterAccountList);
        return "redirect:/account/registration";
    }

    @PostMapping(params = "confirm")
    public String confirm(
            final @Validated RegisterAccountList registerAccountList,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AttributeName.ACCOUNT_CATEGORY_LIST, accountCategories());
            model.addAttribute(AttributeName.REGISTER_ACCOUNT, RegisterAccount.empty());
            return "account/registration";
        }
        return "account/confirm_registration";
    }

    @PostMapping(params = "submit")
    public String submit(
            final @Validated RegisterAccountList registerAccountList,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AttributeName.ACCOUNT_CATEGORY_LIST, accountCategories());
            model.addAttribute(AttributeName.REGISTER_ACCOUNT, RegisterAccount.empty());
            return "account/registration";
        }

        try {
            this.registerAccountService.execute(registerAccountList);
            return "redirect:/account/registration/success";
        } catch (final Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/account/registration/failed";
        }
    }

    @PostMapping(params = "cancel")
    public String cancel(
            final @Validated RegisterAccountList registerAccountList,
            final BindingResult __,
            final RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute(AttributeName.REGISTER_ACCOUNT_LIST, registerAccountList);
        return "redirect:/account/registration";
    }

    @GetMapping("/success")
    public String success() {
        return "account/success_registration";
    }

    @GetMapping("/failed")
    public String failed() {
        return "account/failed_registration";
    }

    private List<AccountCategory> accountCategories() {
        final AccountCategory[] accountCategories = AccountCategory.values();

        // ここでは学生アカウントを扱わないため除外する
        final var accountCategoryList = new ArrayList<>(Arrays.asList(accountCategories));
        accountCategoryList.remove(AccountCategory.STUDENT);

        return accountCategoryList;
    }
}
