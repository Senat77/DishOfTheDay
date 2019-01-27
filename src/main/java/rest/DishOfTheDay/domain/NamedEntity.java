package rest.DishOfTheDay.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public class NamedEntity extends BaseEntity {

    @NotBlank
    @Column (nullable = false, unique = true, length = 100)
    protected String name;

    public NamedEntity() {
    }

    public NamedEntity(String name) {
        this(null, name);
    }

    public NamedEntity(Integer id, String name) {
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
