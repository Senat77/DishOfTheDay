package rest.DishOfTheDay.domain.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VoteRespDTO {

    private Integer id;
    private LocalDate date;
    private Integer menu_id;
}
