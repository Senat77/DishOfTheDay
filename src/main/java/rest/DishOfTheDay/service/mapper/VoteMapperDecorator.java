package rest.DishOfTheDay.service.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import rest.DishOfTheDay.domain.Vote;
import rest.DishOfTheDay.domain.dto.VoteRespDTO;

public abstract class VoteMapperDecorator implements VoteMapper {


    @Autowired
    @Qualifier("delegate")
    private VoteMapper delegate;
}
