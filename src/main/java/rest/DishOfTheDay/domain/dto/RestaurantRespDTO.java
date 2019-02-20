package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RestaurantRespDTO extends BaseEntityDTO {

    private String name;
    private String address;
    private String email;
}
