package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import lombok.NonNull;
import rest.DishOfTheDay.domain.User;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserDTO extends BaseEntityDTO {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private boolean enabled;

    @NotNull
    private Set<User.Role> roles;

    @NotNull
    private LocalDateTime registered;
}
