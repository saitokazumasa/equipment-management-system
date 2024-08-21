package jp.ac.morijyobi.equipmentmanagementsystem.service.implement;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Course;
import jp.ac.morijyobi.equipmentmanagementsystem.mapper.ICoursesMapper;
import jp.ac.morijyobi.equipmentmanagementsystem.service.IListCourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCourseService implements IListCourseService {
    private final ICoursesMapper coursesMapper;

    public ListCourseService(final ICoursesMapper coursesMapper) {
        this.coursesMapper = coursesMapper;
    }

    @Override
    public List<Course> execute() {
        return coursesMapper.selectAll();
    }
}
