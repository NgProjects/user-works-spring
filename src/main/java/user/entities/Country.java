package user.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "country")
@ToString(callSuper=true)
@Getter
@Setter
public class Country extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2089792066560567306L;

    @Column(nullable = false, name = "name", unique = true)
    private String name;

    @Column(nullable = false)
    private String phoneCode;

}
