package rest.DishOfTheDay.domain.base;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;

@MappedSuperclass
@AllArgsConstructor
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

    @Override
    public String toString() {return String.format("Entity %s (%s", getClass().getName(), id);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateEntity that = (DateEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() { return 31;}
}
