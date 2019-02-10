package rest.DishOfTheDay.domain.dto;

/* https://habr.com/ru/post/343960/ */

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
abstract class BaseEntityDTO implements ITransfer {

    @Null(groups = {New.class}) // autogenerated in DB
    @NotNull(groups = {Exist.class})
    private Integer id;
}
