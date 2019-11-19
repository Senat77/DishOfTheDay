package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class PollRespDTO {

    private LocalDate id;

    private Set<MenuRespDTO> menus;
}
