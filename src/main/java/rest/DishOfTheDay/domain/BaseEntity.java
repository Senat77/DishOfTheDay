package rest.DishOfTheDay.domain;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity implements HasId {

    private static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

    public BaseEntity() {
    }

    public BaseEntity(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s", getClass().getName(), id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
