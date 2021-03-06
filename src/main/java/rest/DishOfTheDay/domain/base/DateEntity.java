package rest.DishOfTheDay.domain.base;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;

@MappedSuperclass
@NoArgsConstructor
@Setter
@Getter
public abstract class DateEntity implements ITransfer {

    @Id
    @Column(unique = true, nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Null(groups = {ITransfer.New.class})
    @NotNull(groups = {ITransfer.Exist.class})
    protected LocalDate id;

    @Version
    private Long version;

    public DateEntity (LocalDate date) {id = date;}

    @Override
    public String toString() {return String.format("Entity %s (%s", getClass().getName(), id);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!getClass().isInstance(o)) return false;
        /*
        DateEntity that = (DateEntity) o;
        return id != null && id.equals(that.id);

         */
        return getId() != null && getId().equals(((DateEntity) o).getId());
    }

    @Override
    public int hashCode() { return 31;}
}
