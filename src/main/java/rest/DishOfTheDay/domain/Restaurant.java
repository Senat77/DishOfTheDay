package rest.DishOfTheDay.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;

@NamedQueries({
        @NamedQuery(name = Restaurant.BY_NAME, query = "select r from Restaurant r order by r.name")
})

@Entity
//@Access(AccessType.FIELD)
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "address"}, name = "restaurants_idx")})
public class Restaurant extends AbstractNamedEntity {

    public static final String BY_NAME = "Restaurant.getAllOrderByName";

    @Column (name = "address", unique = true, nullable = false)
    private String address;

    @Column (name = "email", unique = true)
    @Email
    private String email;

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
        super(id, name);
        this.address = address;
        this.email = email;
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
