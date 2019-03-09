package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import rest.DishOfTheDay.domain.base.ITransfer;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntityDTO implements ITransfer {

    @NotNull (groups = {Exist.class})
    @Null (groups = {New.class})
    private Integer id;
}
