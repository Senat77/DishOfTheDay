package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import rest.DishOfTheDay.domain.base.ITransfer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
public class PollReqDTO implements ITransfer {

    @NotNull
    private LocalDate id;

    @NotNull
    private Set<Integer> menu_id;
}
