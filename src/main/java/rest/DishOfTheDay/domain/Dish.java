package rest.DishOfTheDay.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class Dish implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private Integer price;
}
