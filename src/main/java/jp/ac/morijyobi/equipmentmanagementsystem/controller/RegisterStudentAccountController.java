package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterStudentAccount;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.RegisterStudentAccountList;
import jp.ac.morijyobi.equipmentmanagementsystem.service.course.IListCourseService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.stuedent.IRegisterStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/student/registration")
public class RegisterStudentAccountController {
    private final IListCourseService listCourseService;
    private final IRegisterStudentService registerStudentService;

    private static class AttributeName {
        public static final String COURSE_LIST = "courseList";
        public static final String REGISTER_STUDENT_ACCOUNT = "registerStudentAccount";
        public static final String REGISTER_STUDENT_ACCOUNT_LIST = "registerStudentAccountList";
    }

    public RegisterStudentAccountController(
            final IListCourseService listCourseService,
            final IRegisterStudentService registerStudentService
    ) {
        this.listCourseService = listCourseService;
        this.registerStudentService = registerStudentService;
    }

    @GetMapping()
    public String get(final Model model) {
        // リダイレクトの場合はリセットしない
        final var isRedirected = model.containsAttribute(AttributeName.REGISTER_STUDENT_ACCOUNT_LIST);
        final var registerStudentAccountList = isRedirected ?
                (RegisterStudentAccountList) model.getAttribute(AttributeName.REGISTER_STUDENT_ACCOUNT_LIST) :
                RegisterStudentAccountList.empty();

        model.addAttribute(AttributeName.COURSE_LIST, listCourseService.execute());
        model.addAttribute(AttributeName.REGISTER_STUDENT_ACCOUNT, RegisterStudentAccount.empty());
        model.addAttribute(AttributeName.REGISTER_STUDENT_ACCOUNT_LIST, registerStudentAccountList);
        return "student/registration/registration";
    }

    @PostMapping(params = "add")
    public String add(
            final @Validated RegisterStudentAccount registerStudentAccount,
            final BindingResult bindingResult,
            final @Validated RegisterStudentAccountList registerStudentAccountList,
            // これがないと初期状態の空リストを通せない
            final BindingResult __,
            final Model model,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AttributeName.COURSE_LIST, listCourseService.execute());
            return "student/registration/registration";
        }

        // values が null の状態で入ってくることがあるため、その場合は初期化してから追加する
        final RegisterStudentAccountList newRegisterStudentAccountList = registerStudentAccountList.isNull() ?
                RegisterStudentAccountList.empty().add(registerStudentAccount) :
                registerStudentAccountList.add(registerStudentAccount);

        redirectAttributes.addFlashAttribute(AttributeName.REGISTER_STUDENT_ACCOUNT_LIST, newRegisterStudentAccountList);

        return "redirect:/student/registration";
    }

    @PostMapping(params = "remove")
    public String remove(
            final @Validated RegisterStudentAccount registerStudentAccount,
            final BindingResult _1,
            final @Validated RegisterStudentAccountList registerStudentAccountList,
            final BindingResult _2,
            final HttpServletRequest request,
            final RedirectAttributes redirectAttributes
    ) {
        final int index = Integer.parseInt(request.getParameter("remove"));
        final RegisterStudentAccountList newRegisterStudentAccountList = registerStudentAccountList.remove(index);

        redirectAttributes.addFlashAttribute(AttributeName.REGISTER_STUDENT_ACCOUNT_LIST, newRegisterStudentAccountList);

        return "redirect:/student/registration";
    }

    @PostMapping(params = "confirm")
    public String confirm(
            final @Validated RegisterStudentAccountList registerStudentAccountList,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AttributeName.COURSE_LIST, listCourseService.execute());
            model.addAttribute(AttributeName.REGISTER_STUDENT_ACCOUNT, RegisterStudentAccount.empty());
            return "student/registration/registration";
        }

        return "student/registration/confirmation";
    }

    @PostMapping(params = "submit")
    public String submit(
            final @Validated RegisterStudentAccountList registerStudentAccountList,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(AttributeName.COURSE_LIST, listCourseService.execute());
            model.addAttribute(AttributeName.REGISTER_STUDENT_ACCOUNT, RegisterStudentAccount.empty());
            return "student/registration/registration";
        }

        try {
            this.registerStudentService.execute(registerStudentAccountList);
            return "redirect:/student/registration/success";
        } catch (final Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/student/registration/failed";
        }
    }

    @PostMapping(params = "cancel")
    public String cancel(
            final @Validated RegisterStudentAccountList registerStudentAccountList,
            final BindingResult __,
            final RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute(AttributeName.REGISTER_STUDENT_ACCOUNT_LIST, registerStudentAccountList);

        return "redirect:/student/registration";
    }

    @GetMapping("success")
    public String success() {
        return "student/registration/success";
    }

    @GetMapping("failed")
    public String failed() {
        return "student/registration/failed";
    }
}
