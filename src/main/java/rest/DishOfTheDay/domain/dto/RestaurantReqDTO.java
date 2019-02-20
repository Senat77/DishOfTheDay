package rest.DishOfTheDay.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RestaurantReqDTO implements ITransfer {

    @NotBlank(groups = {New.class})
    @Size(min = 3, max = 32)
    private String name;

    @NotBlank(groups = {New.class})
    @Size(min = 3, max = 32)
    private String address;

    @Email
    private String email;
}
