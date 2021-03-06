package rest.DishOfTheDay.service.mapper;


import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.Vote;
import rest.DishOfTheDay.domain.dto.VoteReqDTO;
import rest.DishOfTheDay.domain.dto.VotingResult;
import rest.DishOfTheDay.repository.MenuRepository;
import rest.DishOfTheDay.repository.PollRepository;
import rest.DishOfTheDay.repository.UserRepository;
import rest.DishOfTheDay.repository.VoteRepository;

import java.time.LocalDate;

public abstract class VoteMapperDecorator implements VoteMapper {

    @Autowired
    @Qualifier("delegate")
    private VoteMapper delegate_voteMapper;

    @Autowired
    private VoteRepository repository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Vote toVote(VoteReqDTO voteDTO) {
        Vote vote = delegate_voteMapper.toVote(voteDTO);
        return getVote(voteDTO, vote);
    }

    private Vote getVote(VoteReqDTO voteDTO, Vote vote) {
        vote.setMenu(menuRepository.getOne(voteDTO.getMenu_id()));
        vote.setUser(userRepository.getOne(voteDTO.getUser_id()));
        vote.setPoll(pollRepository.getOne(LocalDate.now()));
        return vote;
    }

    @Override
    public Vote toUpdate(@MappingTarget Vote vote, VoteReqDTO voteReqDTO) {
        Vote updated = delegate_voteMapper.toUpdate(vote, voteReqDTO);
        updated.setMenu(menuRepository.getOne(voteReqDTO.getMenu_id()));
        return updated;
    }
}
