package rest.DishOfTheDay.domain;

import lombok.Getter;
import lombok.Setter;
import rest.DishOfTheDay.domain.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Setter
@Getter
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "address"}, name = "restaurants_idx")})
public class Restaurant extends BaseEntity {

    @NotBlank
    @Column (name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column (name = "address", unique = true, nullable = false)
    private String address;

    @Column (name = "email", unique = true)
    @Email
    private String email;

    public Restaurant() {
    }

    public Restaurant(String name, String address, @Email String email) {
        this(null, name, address, email);
    }

    public Restaurant(Integer id, String name, String address, String email) {
        super(id);
        this.name = name;
        this.address = address;
        this.email = email;
    }
}
