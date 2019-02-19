package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserResponseDTO extends BaseEntityDTO{

    private String name;
    private String email;
}
