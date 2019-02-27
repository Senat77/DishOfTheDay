package rest.DishOfTheDay.service.mapper;

import org.mapstruct.Mapper;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.dto.MenuRespDTO;

import java.util.List;

@Mapper
public interface MenuMapper {

    MenuRespDTO fromMenu (Menu menu);

    List<MenuRespDTO> fromMenus(List<Menu> menus);
}
