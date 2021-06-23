package user.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(of="id")
@ToString
@Access(AccessType.FIELD)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8810035151790632242L;

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    @Access(AccessType.PROPERTY)
    private Long id;

    @Column(nullable = false)
    private boolean deleted = false;

    @Column(name="date_created", updatable = false)
    private Timestamp dateCreated = new Timestamp(System.currentTimeMillis());

    @Column(name="date_modified")
    private Timestamp dateModified = new Timestamp(System.currentTimeMillis());

}
