package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import rest.DishOfTheDay.domain.Dish;
import rest.DishOfTheDay.domain.base.ITransfer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class MenuReqDTO implements ITransfer {

    @NotNull
    private Integer restaurant_id;

    @NotNull
    private LocalDate date;

    @NotNull
    @NotBlank
    private List<Dish> dishes;
}
