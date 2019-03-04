package rest.DishOfTheDay.domain.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class PollRespDTO {

    private LocalDate id;

    private Set<MenuRespDTO> menus;
}
