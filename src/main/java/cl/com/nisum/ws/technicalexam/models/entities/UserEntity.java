package cl.com.nisum.ws.technicalexam.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Entidad de Usuario
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Entity
@Table(name = "NSM_USER", schema = "PUBLIC")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntity {

    @Id
    @Column(name = "USR_ID", nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "USR_NAME", nullable = false, columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name = "USR_EMAIL", nullable = false, unique = true, columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(name = "USR_PASSWORD", nullable = false, columnDefinition = "VARCHAR(75)")
    private String password;

    @Column(name = "USR_STATUS", columnDefinition = "TINYINT", nullable = false)
    private Integer status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USR_LAST_LOGIN", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date lastLogin;

    @Column(name = "USR_USER_CREATED", nullable = false, columnDefinition = "VARCHAR(100)")
    private String userCreated;

    @Column(name = "USR_USER_UPDATED", nullable = false, columnDefinition = "VARCHAR(100)")
    private String userUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USR_DATE_CREATED", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USR_DATE_UPDATED", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date dateUpdated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<PhoneEntity> phones;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "NSM_USER_IN_ROLE",
            joinColumns = @JoinColumn(name = "UIR_USR_ID", columnDefinition = "VARCHAR(36)"),
            inverseJoinColumns = @JoinColumn(name = "UIR_ROL_ID", columnDefinition = "VARCHAR(36)")

    )
    private Set<RoleEntity> roles;
}
