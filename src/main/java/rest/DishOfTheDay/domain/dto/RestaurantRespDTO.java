package rest.DishOfTheDay.domain.dto;

import lombok.Data;

@Data
public class RestaurantRespDTO {

    private Integer id;
    private String name;
    private String address;
    private String email;
}
