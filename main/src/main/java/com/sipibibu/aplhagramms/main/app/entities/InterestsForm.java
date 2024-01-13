package com.sipibibu.aplhagramms.main.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@Table(name="interests")
@NoArgsConstructor
public class InterestsForm {
    @Id
    private Long id;

    public InterestsForm(Long interst){
        this.id=interst;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        return ((InterestsForm) obj).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
