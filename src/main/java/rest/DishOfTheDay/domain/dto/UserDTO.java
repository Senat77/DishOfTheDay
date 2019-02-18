package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import rest.DishOfTheDay.domain.User;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
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
    private boolean enabled;

    @NotNull
    private Set<User.Role> roles;

    @NotNull
    private LocalDateTime registered;
}
