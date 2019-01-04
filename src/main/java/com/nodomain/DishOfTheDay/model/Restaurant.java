package com.nodomain.DishOfTheDay.model;

import javax.persistence.Entity;

@Entity
public class Restaurant extends AbstractNamedEntity {

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id + '\'' +
                ", name='" + name +
                '}';
    }
}
