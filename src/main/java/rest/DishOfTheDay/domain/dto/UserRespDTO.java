package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRespDTO extends BaseEntityDTO{

    private String name;
    private String email;
}
