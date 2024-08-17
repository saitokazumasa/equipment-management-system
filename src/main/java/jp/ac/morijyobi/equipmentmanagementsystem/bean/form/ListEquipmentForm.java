package jp.ac.morijyobi.equipmentmanagementsystem.bean.form;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class ListEquipmentForm {
    @Size(min = 0, max = 255)
    private String name;

    private List<Integer> categoryIdList;

    private List<Integer> statusIdList;
}
