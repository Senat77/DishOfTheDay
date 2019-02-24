package rest.DishOfTheDay.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Access(AccessType.FIELD)
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

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "restaurant_id")
    private List<Dish> dishes = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(Restaurant restaurant) {
        this.id = restaurant.id;
        this.name = restaurant.name;
        this.address = restaurant.address;
        this.email = restaurant.email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email +
                '}';
    }
}
