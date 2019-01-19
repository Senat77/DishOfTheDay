package rest.DishOfTheDay.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rest.DishOfTheDay.domain.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Restaurant> getAllOrderByName() {
        return em.createNamedQuery(Restaurant.BY_NAME, Restaurant.class).getResultList();
    }

    public Restaurant get(Integer id) {
        return em.find(Restaurant.class, id);
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if(restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }
}
