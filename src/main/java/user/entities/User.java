package user.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import user.utility.PasswordUtility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "user_details")
@ToString(callSuper=true)
@Getter
@Setter
public class User extends BaseEntity implements UserDetails, Serializable {

    private static final long serialVersionUID = -4571143655407670703L;

    @Column(nullable = false, name = "full_name")
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name="phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(name = "j_verifier")
    private String tokenVerifier;

    @Column(name="last_login")
    private Timestamp lastLogin;

    @JoinColumn(name = "country_fk")
    private Country country;

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = PasswordUtility.hashPassword(password);
    }

    /**
     *
     * @param password
     * @return
     */
    public boolean isValidPassword(String password) {
        return this.password.equalsIgnoreCase(PasswordUtility.hashPassword(password));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
