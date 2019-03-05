package rest.DishOfTheDay.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import rest.DishOfTheDay.domain.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "votes")
public class Vote extends BaseEntity {

    @ManyToOne
    private Poll poll;

    @ManyToOne
    private User user;

    @ManyToOne
    private Menu menu;

    @Column(columnDefinition = "timestamp default now()", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime registered;

    public Vote() {
        this(null, null, null);
    }

    public Vote(Poll poll, User user, Menu menu) {
        this(poll, user, menu, LocalDateTime.now());
    }
}
