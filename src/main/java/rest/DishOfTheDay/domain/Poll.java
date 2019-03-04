package rest.DishOfTheDay.domain;

import lombok.Getter;
import lombok.Setter;
import rest.DishOfTheDay.domain.base.DateEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "polls")
public class Poll extends DateEntity {

    @NotNull
    @ManyToMany
    private Set<Menu> menus;

    public Poll() {
    }

    public Poll(LocalDate date, @NotNull Set<Menu> menus) {
        super(date);
        this.menus = menus;
    }

    public Poll(@NotNull Set<Menu> menus) {
        this(null, menus);
    }
}
