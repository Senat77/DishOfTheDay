package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class RestaurantReqDTO extends BaseEntityDTO {

    @NotBlank(groups = {New.class})
    @Size(min = 3, max = 32)
    private String name;

    @NotBlank(groups = {New.class})
    @Size(min = 3, max = 32)
    private String address;

    @Email
    private String email;

    public RestaurantReqDTO(@NotBlank(groups = {New.class}) @Size(min = 3, max = 32) String name,
                            @NotBlank(groups = {New.class}) @Size(min = 3, max = 32) String address,
                            @Email String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }
}
