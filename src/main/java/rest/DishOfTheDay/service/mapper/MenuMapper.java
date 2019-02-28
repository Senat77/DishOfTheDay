package rest.DishOfTheDay.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.dto.MenuReqDTO;
import rest.DishOfTheDay.domain.dto.MenuRespDTO;

import java.util.List;

@Mapper
@DecoratedWith(MenuMapperDecorator.class)
public interface MenuMapper {

    MenuRespDTO fromMenu (Menu menu);

    Menu toMenu (MenuReqDTO menuDTO);

    List<MenuRespDTO> fromMenus(List<Menu> menus);
}
