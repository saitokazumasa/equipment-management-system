package jp.ac.morijyobi.equipmentmanagementsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.JsonTypeHandler;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.*;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ISampleMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class GeneralController {

    private final ISampleMapper sampleMapper;

    public GeneralController(final ISampleMapper sampleMapper) {
        this.sampleMapper = sampleMapper;
    }

    @GetMapping("/")
    public String get(final Model model) {
        final var course = new Course(-1, "testCourse");
//        final var sample = new Sample(-1, "test", course);
//
//        sampleMapper.insert(sample);
//

        final var sampleList = sampleMapper.selectAll();
//        List<Sample> sampleList = new ArrayList<>();
//        sampleList.add(new Sample(1, "name", course));

        model.addAttribute("sampleList", sampleList);
        return "index";
    }
}
