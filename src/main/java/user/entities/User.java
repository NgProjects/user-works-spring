package user.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import user.utility.PasswordUtility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name ="user_details")
@ToString(callSuper=true)
@Getter
@Setter
public class User extends BaseEntity {

    private static final long serialVersionUID = -4571143655407670703L;

    @Column(nullable = false, name = "full_name")
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name="phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(name="last_login")
    private Timestamp lastLogin;

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

}
