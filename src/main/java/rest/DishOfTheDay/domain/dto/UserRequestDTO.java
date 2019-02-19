package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRequestDTO extends BaseEntityDTO {

    @NotBlank
    @Size(min = 3, max = 32)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 16)
    private String password;
}
