package rest.DishOfTheDay.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static org.springframework.security.config.Elements.PASSWORD_ENCODER;

@Entity
@Table(name = "users")
@Setter
@Getter
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

    /*
    @NotNull
    @Column(nullable = false)
    private Role role = ROLE_USER;
    */

    @NotNull
    @Column(nullable = false)
    private LocalDateTime registered;

    /*
    public User(@NotNull String email, @NotNull String name, @NotNull String password, @NotNull boolean enabled, Role role, @NotNull LocalDateTime registered) {
        this.email = email;
        this.name = name;
        setPassword(password);
        this.enabled = enabled;
        //this.role = role;
        this.registered = registered;
    }
    */

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    /*
    @PrePersist
    private void encodePassword() {
        password = (password != null) ? PASSWORD_ENCODER.encode(password) : null;
    }


    public enum Role implements GrantedAuthority {

        @Override
        public String getAuthority() {
            return null;
        }
    }
    */
}
