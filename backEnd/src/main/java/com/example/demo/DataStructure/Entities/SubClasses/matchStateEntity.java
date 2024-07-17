package com.example.demo.DataStructure.Entities.SubClasses;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Embeddable
@Data
public class matchStateEntity {

    public long entityId;

    public String state;


    public matchStateEntity(long entityId, String state) {
        this.entityId = entityId;
        this.state = state;
    }

    public matchStateEntity() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        matchStateEntity that = (matchStateEntity) o;
        return entityId == that.entityId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityId);
    }
}
