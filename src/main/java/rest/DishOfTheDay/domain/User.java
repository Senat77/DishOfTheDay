package rest.DishOfTheDay.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import rest.DishOfTheDay.config.SecurityConfig;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "id"))
    @ElementCollection
    private Set<Role> roles;

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
