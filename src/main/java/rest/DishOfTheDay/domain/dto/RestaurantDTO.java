package rest.DishOfTheDay.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class RestaurantDTO extends BaseEntityDTO {

    @NotNull
    private String name;

    @NotNull
    private String address;

    @Email
    private String email;
}
