package rest.DishOfTheDay.domain.base;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@MappedSuperclass
@TypeDef(name = "json", typeClass = JsonStringType.class)
public abstract class BaseEntity implements ITransfer {

    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Null(groups = {New.class})
    @NotNull(groups = {Exist.class})
    protected Integer id;

    public BaseEntity() {
    }

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

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
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
