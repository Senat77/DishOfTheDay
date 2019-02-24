package rest.DishOfTheDay.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Access(AccessType.FIELD)
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "restaurant_id"}, name = "unique_dishes")})
public class Dish extends BaseEntity {

    @NotBlank
    @Column (name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne
    private Restaurant restaurant;

    @NotNull
    @Column (name = "price", nullable = false)
    private Integer price = 0;

    public Dish(@NotBlank String name, int price, @NotBlank Restaurant restaurant) {
        super(null);
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
