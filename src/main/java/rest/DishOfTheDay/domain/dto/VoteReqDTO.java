package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import rest.DishOfTheDay.domain.base.ITransfer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class VoteReqDTO implements ITransfer {

    @NotNull
    private Integer menu_id;

    @Null(groups = {New.class})
    @NotNull(groups = {Exist.class})
    private Integer user_id;

    public VoteReqDTO(@NotNull Integer menu_id, @Null(groups = {New.class}) @NotNull(groups = {Exist.class}) Integer user_id) {
        this.menu_id = menu_id;
        this.user_id = user_id;
    }
}
