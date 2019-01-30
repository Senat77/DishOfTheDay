package rest.DishOfTheDay.domain.dto;

/* https://habr.com/ru/post/343960/ */

import lombok.Data;

import javax.validation.constraints.Null;

@Data
public class BaseEntityDTO {

    @Null
    private Integer Id;
}
