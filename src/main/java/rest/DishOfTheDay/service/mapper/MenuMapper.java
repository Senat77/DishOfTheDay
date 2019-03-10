package rest.DishOfTheDay.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.dto.MenuReqDTO;
import rest.DishOfTheDay.domain.dto.MenuRespDTO;

import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        nullValueCheckStrategy = ALWAYS,
        nullValueMappingStrategy = RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = IGNORE
)

@DecoratedWith(MenuMapperDecorator.class)
public interface MenuMapper {

    MenuRespDTO fromMenu (Menu menu);

    Menu toMenu (MenuReqDTO menuDTO);

    Menu toUpdate(@MappingTarget Menu menu, MenuReqDTO menuDTO);

    List<MenuRespDTO> fromMenus(List<Menu> menus);
}
