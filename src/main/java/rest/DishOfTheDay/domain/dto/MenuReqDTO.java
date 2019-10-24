package rest.DishOfTheDay.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import rest.DishOfTheDay.domain.Dish;
import rest.DishOfTheDay.domain.base.ITransfer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class MenuReqDTO implements ITransfer {

    @NotNull
    private Integer restaurant_id;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    @NotBlank
    private List<Dish> dishes;
}
