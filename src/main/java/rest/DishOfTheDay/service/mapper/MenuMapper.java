package rest.DishOfTheDay.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.dto.MenuRespDTO;

@Mapper
public interface MenuMapper {

    MenuRespDTO fromMenu (Menu menu);
}
