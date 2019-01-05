package rest.DishOfTheDay.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractNamedEntity extends AbstractBaseEntity {

    @Column (nullable = false, unique = true, length = 100)
    protected String name;

    public AbstractNamedEntity() {
    }

    public AbstractNamedEntity(String name) {
        this(null, name);
    }

    public AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, %s)", getClass().getName(), id, name);
    }
}
