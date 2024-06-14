package jp.ac.morijyobi.equipmentmanagementsystem.bean.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Sample {
    private int id;
    private final String name;
    private final String course;

    public Sample(final int id, final String name, final String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public Course getCourseObject() {
        return tryToCourse(course);
    }

    private Course tryToCourse(final String json) {
        try {
            final var objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
            return objectMapper.readValue(json, Course.class);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void setId(final int id) {
        this.id = id;
    }
}
