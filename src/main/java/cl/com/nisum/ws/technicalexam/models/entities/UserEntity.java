package cl.com.nisum.ws.technicalexam.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Entidad de Usuario")
public class UserEntity {

    @Id
    @Column(name = "USR_ID", nullable = false, columnDefinition = "VARCHAR(36)")
    @Schema(description = "Id del usuario en formato UUID", example = "cfa0bc5e-5f83-4bbf-85e2-ffa9d676dc4c", required = true)
    private String id;

    @Column(name = "USR_NAME", nullable = false, columnDefinition = "VARCHAR(100)")
    @Schema(description = "Nombre del usuario", example = "Anthony Martin Rosas Quispe", required = true)
    private String name;

    @Column(name = "USR_EMAIL", nullable = false, unique = true, columnDefinition = "VARCHAR(100)")
    @Schema(description = "Correo del usuario", example = "test@test.com", required = true)
    private String email;

    @Column(name = "USR_PASSWORD", nullable = false, columnDefinition = "VARCHAR(75)")
    @Schema(description = "Contraseña del usuario", example = "latestAA153_", required = true)
    private String password;

    @Column(name = "USR_STATUS", columnDefinition = "TINYINT", nullable = false)
    @Schema(description = "Estado del usuario:  Activo -->  1, Inactivo --> 0", required = true)
    private Integer status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USR_LAST_LOGIN", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    @Schema(description = "Fecha del ultimo log-in del usuario", example = "20-02-2019T07:00:00Z")
    private Date lastLogin;

    @Column(name = "USR_USER_CREATED", nullable = false, columnDefinition = "VARCHAR(100)")
    @Schema(description = "Usuario quien creó el usuario", example = "test@test.com", required = true)
    private String userCreated;

    @Column(name = "USR_USER_UPDATED", nullable = false, columnDefinition = "VARCHAR(100)")
    @Schema(description = "Usuario quien actualizó el usuario", example = "test@test.com", required = true)
    private String userUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USR_DATE_CREATED", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    @Schema(description = "Fecha de cuando creó el usuario", example = "20-02-2019T07:00:00Z")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USR_DATE_UPDATED", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @Schema(description = "Fecha de cuando actualizó el usuario", example = "20-02-2020T07:00:00Z")
    private Date dateUpdated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Schema(description = "Lista de teléfonos asociados al usuario")
    private List<PhoneEntity> phones;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "NSM_USER_IN_ROLE",
            joinColumns = @JoinColumn(name = "UIR_USR_ID", columnDefinition = "VARCHAR(36)"),
            inverseJoinColumns = @JoinColumn(name = "UIR_ROL_ID", columnDefinition = "VARCHAR(36)")

    )
    @Schema(description = "Lista de roles del usuario", required = true)
    private Set<RoleEntity> roles;
}
