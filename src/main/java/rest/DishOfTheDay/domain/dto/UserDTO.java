package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import rest.DishOfTheDay.domain.User;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Set;

@Data
public class UserDTO extends BaseEntityDTO {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull(groups = {toObject.class})
    @Null(groups = {fromObject.class})
    private String password;

    @NotNull
    private Set<User.Role> roles;
}
