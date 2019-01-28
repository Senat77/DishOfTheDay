package rest.DishOfTheDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rest.DishOfTheDay.domain.Restaurant;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    /*
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

    @Transactional
    public boolean delete(Integer id) {
        return em.createNamedQuery(Restaurant.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }
    */
}
