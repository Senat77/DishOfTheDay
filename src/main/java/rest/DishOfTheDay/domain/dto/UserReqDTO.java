package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import rest.DishOfTheDay.domain.base.ITransfer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserReqDTO implements ITransfer {

    @NotBlank(groups = {New.class})
    @Size(min = 3, max = 32)
    private String name;

    @NotBlank(groups = {New.class})
    @Email
    private String email;

    @NotBlank(groups = {New.class})
    @Size(min = 6, max = 16)
    private String password;
}
