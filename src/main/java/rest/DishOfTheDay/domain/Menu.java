package rest.DishOfTheDay.domain;

import lombok.*;
import org.hibernate.annotations.Type;
import rest.DishOfTheDay.domain.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Access(AccessType.FIELD)
@Table(name = "menus", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "restaurant_id"}, name = "menus_idx")})
public class Menu extends BaseEntity {

    @NotNull
    @Column (name = "date", nullable = false)
    private LocalDate date;

    @NonNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @NotNull
    @Type(type = "json")
    @Column(columnDefinition = "clob")
    private List<Dish> dishes;

    public Menu() {
    }

    public Menu(@NotNull LocalDate date, @NonNull Restaurant restaurant, List<Dish> dishes) {
        this.id = null;
        this.date = date;
        this.restaurant = restaurant;
        this.dishes = dishes;
    }

    public Menu(@NotNull Restaurant restaurant, List<Dish> dishes) {
        this(LocalDate.now(), restaurant, dishes);
    }
}
