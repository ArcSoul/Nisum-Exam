package cl.com.nisum.ws.technicalexam.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Entidad de Rol
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Entity
@Table(name = "NSM_ROLE")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Entidad de Rol")
public class RoleEntity {

    @Id
    @Column(name = "ROL_ID", nullable = false, columnDefinition = "VARCHAR(36)")
    @Schema(description = "Id del rol en formato UUID", example = "cfa0bc5e-5f83-4bbf-85e2-ffa9d676dc4c", required = true)
    private String id;

    @Column(name = "ROL_NAME", nullable = false, columnDefinition = "VARCHAR(20)")
    @Schema(description = "Nombre del rol", example = "ROLE_MODERATOR", required = true)
    private String name;

    @Column(name = "ROL_USER_CREATED", nullable = false, columnDefinition = "VARCHAR(100)")
    @Schema(description = "Usuario quien creó el rol", example = "test@test.com", required = true)
    private String userCreated;

    @Column(name = "ROL_USER_UPDATED", nullable = false, columnDefinition = "VARCHAR(100)")
    @Schema(description = "Usuario quien actualizó el rol", example = "test@test.com", required = true)
    private String userUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ROL_DATE_CREATED", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    @Schema(description = "Fecha de cuando creó el rol", example = "20-02-2019T07:00:00Z")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ROL_DATE_UPDATED", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @Schema(description = "Fecha de cuando actualizó el rol", example = "20-02-2020T07:00:00Z")
    private Date dateUpdated;
}
