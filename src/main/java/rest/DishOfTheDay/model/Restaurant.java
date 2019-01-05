package rest.DishOfTheDay.model;

import javax.persistence.Entity;

@Entity
public class Restaurant extends AbstractNamedEntity {

    public Restaurant() {
    }

    public Restaurant(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id + '\'' +
                ", name='" + name +
                '}';
    }
}
