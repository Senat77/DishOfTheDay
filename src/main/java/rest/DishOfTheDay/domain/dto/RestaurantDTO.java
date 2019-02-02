package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class RestaurantDTO extends BaseEntityDTO {

    @NotNull(groups = {New.class, Exist.class})
    private String name;

    @NotNull(groups = {New.class, Exist.class})
    private String address;

    @Email
    private String email;
}
