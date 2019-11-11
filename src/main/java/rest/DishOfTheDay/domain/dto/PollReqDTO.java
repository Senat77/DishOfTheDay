package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import rest.DishOfTheDay.domain.base.ITransfer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class PollReqDTO implements ITransfer {

    @NotNull
    private LocalDate id;

    @NotNull
    private Set<Integer> menu_id;

    public PollReqDTO(@NotNull LocalDate id, @NotNull Set<Integer> menu_id) {
        this.id = id;
        this.menu_id = menu_id;
    }
}
