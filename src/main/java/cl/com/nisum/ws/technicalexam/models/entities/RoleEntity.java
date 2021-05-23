package cl.com.nisum.ws.technicalexam.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class RoleEntity {

    @Id
    @Column(name = "ROL_ID", nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "ROL_NAME", nullable = false, columnDefinition = "VARCHAR(20)")
    private String name;

    @Column(name = "ROL_USER_CREATED", nullable = false, columnDefinition = "VARCHAR(100)")
    private String userCreated;

    @Column(name = "ROL_USER_UPDATED", nullable = false, columnDefinition = "VARCHAR(100)")
    private String userUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ROL_DATE_CREATED", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ROL_DATE_UPDATED", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date dateUpdated;
}
