package rest.DishOfTheDay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.Vote;
import rest.DishOfTheDay.domain.VotingHistory;
import rest.DishOfTheDay.domain.dto.MenuWithVotes;
import rest.DishOfTheDay.domain.dto.VotingResult;
import rest.DishOfTheDay.repository.VoteRepository;
import rest.DishOfTheDay.repository.VotingHistoryRepository;
import rest.DishOfTheDay.service.mapper.MenuMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class VotingResultService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteRepository voteRepository;

    private final VotingHistoryRepository votingHistoryRepository;

    private final MenuMapper mapper;

    @Autowired
    public VotingResultService(VoteRepository voteRepository, VotingHistoryRepository votingHistoryRepository, MenuMapper mapper) {
        this.voteRepository = voteRepository;
        this.votingHistoryRepository = votingHistoryRepository;
        this.mapper = mapper;
    }

    @Transactional
    public VotingResult getResult(LocalDate date) {
        if(date.isBefore(LocalDate.now())) {
            return votingHistoryRepository.findById(date).get().getVotingResult();
        }
        else {
            List<Vote> voteCounter = voteRepository.findByPollId(date);
            Map<Menu, Long> map = voteCounter.stream()
                    .collect(Collectors.groupingBy(Vote::getMenu, Collectors.counting()));
            List<MenuWithVotes> list = new ArrayList<>();
            map.forEach((menu, aLong) -> list.add(new MenuWithVotes(mapper.fromMenu(menu), aLong)));
            VotingResult result = new VotingResult(date, list);

            Optional<VotingHistory> optionalVotingHistory = votingHistoryRepository.findById(date);
            if(optionalVotingHistory.isPresent()) {
                VotingHistory votingHistory = optionalVotingHistory.get();
                votingHistory.setVotingResult(result);
            }
            else {
                votingHistoryRepository.save(new VotingHistory(date, result));
            }

            return result;
        }
    }
}
