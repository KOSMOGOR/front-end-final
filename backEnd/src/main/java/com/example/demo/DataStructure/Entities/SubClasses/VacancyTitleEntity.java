package com.example.demo.DataStructure.Entities.SubClasses;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class VacancyTitleEntity {

    public String title;

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacancyTitleEntity that = (VacancyTitleEntity) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
