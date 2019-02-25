package rest.DishOfTheDay.domain.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public abstract class DateEntity {

    @Id
    @Column(unique = true, nullable = false) //columnDefinition = "date default now()", - unsupported in MySQL
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Null(groups = {ITransfer.New.class})
    @NotNull(groups = {ITransfer.Exist.class})
    protected LocalDate date;
}
