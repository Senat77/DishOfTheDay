package rest.DishOfTheDay.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private boolean enabled = true;

    @NotNull
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "id"))
    @ElementCollection
    private Set<Role> roles;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime registered;

    public enum Role implements GrantedAuthority {

        ROLE_USER ("USER"),
        ROLE_ADMIN ("ADMIN");

        private String title;

        Role(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Role{" +
                    "title='" + title + '\'' +
                    '}';
        }

        @Override
        public String getAuthority() {
            return name();
        }
    }
}
