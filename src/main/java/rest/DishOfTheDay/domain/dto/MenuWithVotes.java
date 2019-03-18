package rest.DishOfTheDay.domain.dto;

import lombok.Data;

@Data
public class MenuWithVotes {
    MenuRespDTO menuRespDTO;
    Long voteCounter;

    public MenuWithVotes(MenuRespDTO menuRespDTO, Long counter) {
        this.menuRespDTO = menuRespDTO;
        voteCounter = counter;
    }
}
