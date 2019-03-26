package rest.DishOfTheDay.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import rest.DishOfTheDay.domain.base.DateEntity;
import rest.DishOfTheDay.domain.dto.VotingResult;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "voting_history")
public class VotingHistory extends DateEntity {

    @NotNull
    @Type(type = "json")
    @Column(columnDefinition = "clob")
    private VotingResult votingResult;

    public VotingHistory() {
    }

    public VotingHistory (@NotNull LocalDate date, @NotNull VotingResult votingResult) {
        this.id = date;
        this.votingResult = votingResult;
    }
}
