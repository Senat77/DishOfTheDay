package rest.DishOfTheDay.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import rest.DishOfTheDay.domain.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
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
    private Set<Role> roles = Set.of(Role.ROLE_USER);

    public User() {
    }

    public User(Integer id, @NotNull String name, @NotNull String password, @NotNull String email, @NotNull Set<Role> roles) {
        super(id);
        this.name = name;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public User(@NotNull String name, @NotNull String password, @NotNull String email, Set<Role> roles) {
        this(null, name, password, email, roles);
    }

    public enum Role implements GrantedAuthority {

        ROLE_USER ("USER"),
        ROLE_ADMIN ("ADMIN");

        private String title;

        Role(String title) {
            this.title = title;
        }

        @Override
        public String getAuthority() {
            return name();
        }
    }
}
