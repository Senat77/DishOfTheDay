package rest.DishOfTheDay.domain;

import org.hibernate.annotations.Type;
import rest.DishOfTheDay.domain.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Access(AccessType.FIELD)
@Table(name = "menues", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "restaurant_id"}, name = "menus_idx")})
public class Menu extends BaseEntity {

    @NotBlank
    @Column (name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Restaurant restaurant;

    /*
    @Type(type = "json")
    @Column(columnDefinition = "clob")
    private List<Dish> dishes;
    */

    @Type(type = "json")
    @Column(columnDefinition = "clob")
    private String dishes;
}
