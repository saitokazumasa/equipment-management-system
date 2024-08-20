package jp.ac.morijyobi.equipmentmanagementsystem.bean.dto;

import jakarta.validation.constraints.Size;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.EquipmentCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentSearchCriteria implements Serializable {
    @Size(max = 255)
    private String name;

    private List<Integer> equipmentCategoryIdList;
    private List<EquipmentState> equipmentStateList;

    public static EquipmentSearchCriteria generate(
            final List<Integer> equipmentCategoryIdList,
            final List<EquipmentState> equipmentStateList
    ) {
        return new EquipmentSearchCriteria("", equipmentCategoryIdList, equipmentStateList);
    }
}
