package rest.DishOfTheDay.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class VotingResult {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    List<MenuWithVotes> menuWithVotes = new ArrayList<>();

    public VotingResult(LocalDate date, List<MenuWithVotes> menuWithVotes) {
        this.date = date;
        this.menuWithVotes = menuWithVotes;
    }

    @JsonIgnore
    public MenuWithVotes getWinner() {
        if (menuWithVotes.isEmpty())
            return null;
        MenuWithVotes res = menuWithVotes.get(0);
        for (MenuWithVotes e : menuWithVotes) {
            if (e.voteCounter > res.voteCounter)
                res = e;
        }
        return res;
    }
}
