package rest.DishOfTheDay.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.dto.MenuReqDTO;
import rest.DishOfTheDay.domain.dto.MenuRespDTO;

import java.util.List;

@Mapper
public interface MenuMapper {

    MenuRespDTO fromMenu (Menu menu);

    //@Mapping(target = "restaurant.id", source = "restaurant_id")
    Menu toMenu (MenuReqDTO menuDTO);

    List<MenuRespDTO> fromMenus(List<Menu> menus);
}
