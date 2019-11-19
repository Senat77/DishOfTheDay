package rest.DishOfTheDay.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import rest.DishOfTheDay.domain.Dish;
import java.time.LocalDate;
import java.util.List;

@Data
public class MenuRespDTO {

    private Integer id;

    private RestaurantRespDTO restaurant;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private List<Dish> dishes;
}
