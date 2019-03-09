package rest.DishOfTheDay.domain.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RestaurantReqDTO extends BaseEntityDTO {

    @NotBlank(groups = {New.class})
    @Size(min = 3, max = 32)
    private String name;

    @NotBlank(groups = {New.class})
    @Size(min = 3, max = 32)
    private String address;

    @Email
    private String email;
}
