package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Account;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.dto.EquipmentSearchCriteria;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.AccountCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IGetAccountService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IListEquipmentService;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IListEquipmentCategoryService;
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
@RequestMapping("/equipment/list")
public class ListEquipmentController extends BaseController {
    private final IListEquipmentCategoryService listEquipmentCategoryService;
    private final IListEquipmentService listEquipmentService;
    private final IGetAccountService getAccountService;

    private static class AttributeName {
        public static String EQUIPMENT_STATE_LIST = "equipmentStateList";
        public static String EQUIPMENT_CATEGORY_LIST = "equipmentCategoryList";
        public static String EQUIPMENT_LIST = "equipmentList";
        public static String EQUIPMENT_SEARCH_CRITERIA = "equipmentSearchCriteria";
    }

    public ListEquipmentController(
            final IListEquipmentCategoryService listEquipmentCategoryService,
            final IListEquipmentService listEquipmentService,
            final IGetAccountService getAccountService
    ) {
        this.listEquipmentCategoryService = listEquipmentCategoryService;
        this.listEquipmentService = listEquipmentService;
        this.getAccountService = getAccountService;
    }

    @GetMapping()
    public String get(
            final @AuthenticationPrincipal UserDetails userDetails,
            final Model model
    ) {
        final Account account = this.getAccountService.executeByMail(userDetails.getUsername());
        final List<EquipmentState> states = equipmentStateList(account.category);
        final List<Integer> categoryIds = listEquipmentCategoryService.ids();
        final EquipmentSearchCriteria searchCriteria = EquipmentSearchCriteria.generate(categoryIds, states);

        model.addAttribute(AttributeName.EQUIPMENT_STATE_LIST, states);
        model.addAttribute(AttributeName.EQUIPMENT_CATEGORY_LIST, listEquipmentCategoryService.execute());
        model.addAttribute(AttributeName.EQUIPMENT_LIST, listEquipmentService.execute());
        model.addAttribute(AttributeName.EQUIPMENT_SEARCH_CRITERIA, searchCriteria);
        return "equipment/list";
    }

    @PostMapping()
    public String post(
            final @AuthenticationPrincipal UserDetails userDetails,
            final @Validated EquipmentSearchCriteria equipmentSearchCriteria,
            final BindingResult bindingResult,
            final Model model
    ) {
        if (bindingResult.hasErrors()) {
            final Account account = this.getAccountService.executeByMail(userDetails.getUsername());

            model.addAttribute(AttributeName.EQUIPMENT_STATE_LIST, equipmentStateList(account.category));
            model.addAttribute(AttributeName.EQUIPMENT_CATEGORY_LIST, listEquipmentCategoryService.execute());
            model.addAttribute(AttributeName.EQUIPMENT_LIST, listEquipmentService.execute());
            return "equipment/list";
        }

        final Account account = this.getAccountService.executeByMail(userDetails.getUsername());
        final List<Equipment> equipments = listEquipmentService.search(equipmentSearchCriteria);

        model.addAttribute(AttributeName.EQUIPMENT_STATE_LIST, equipmentStateList(account.category));
        model.addAttribute(AttributeName.EQUIPMENT_CATEGORY_LIST, listEquipmentCategoryService.execute());
        model.addAttribute(AttributeName.EQUIPMENT_LIST, equipments);
        return "equipment/list";
    }

    private List<EquipmentState> equipmentStateList(final AccountCategory accountCategory) {
        final var equipmentStates = new EquipmentState[][] {
                // AccountCategory.STUDENT
                new EquipmentState[] { EquipmentState.ON_LOAN, EquipmentState.AVAILABLE_FOR_LOAN },
                // AccountCategory.USER
                new EquipmentState[] { EquipmentState.ON_LOAN, EquipmentState.AVAILABLE_FOR_LOAN },
                // AccountCategory.EQUIPMENT_MANAGER
                new EquipmentState[] {
                        EquipmentState.ON_LOAN,
                        EquipmentState.AVAILABLE_FOR_LOAN,
                        EquipmentState.NOT_AVAILABLE_FOR_LOAN
                },
                // AccountCategory.SYSTEM_MANAGER
                new EquipmentState[] {
                        EquipmentState.ON_LOAN,
                        EquipmentState.AVAILABLE_FOR_LOAN,
                        EquipmentState.NOT_AVAILABLE_FOR_LOAN
                },
        };
        return Arrays.asList(equipmentStates[accountCategory.ordinal()]);
    }
}