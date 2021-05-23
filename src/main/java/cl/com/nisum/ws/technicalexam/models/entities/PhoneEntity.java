package cl.com.nisum.ws.technicalexam.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Entidad de Teléfono
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Entity
@Table(name = "NSM_PHONE", schema = "PUBLIC")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Entidad de Teléfono")
public class PhoneEntity {

    @Id
    @Column(name = "POE_ID", nullable = false, columnDefinition = "VARCHAR(36)")
    @Schema(description = "Id del teléfono en formato UUID", example = "cfa0bc5e-5f83-4bbf-85e2-ffa9d676dc4c", required = true)
    private String id;

    @Column(name = "POE_NUMBER", nullable = false, columnDefinition = "VARCHAR(15)")
    @Schema(description = "Número de telefono del usuario", example = "+51940792465", required = true)
    private String number;

    @Column(name = "POE_CITY_CODE", nullable = false, columnDefinition = "INT")
    @Schema(description = "Código de la ciudad", example = "1", required = true)
    private Integer cityCode;

    @Column(name = "POE_COUNTRY_CODE", nullable = false, columnDefinition = "INT")
    @Schema(description = "Código del país", example = "12", required = true)
    private Integer countryCode;

    @ManyToOne
    @JoinColumn(name = "POE_USR_ID", nullable = false, updatable = false)
    @Schema(description = "Usuario enlazado al teléfono", required = true)
    private UserEntity user;

    @Column(name = "POE_USER_CREATED", nullable = false, columnDefinition = "VARCHAR(100)")
    @Schema(description = "Usuario quien creó el teléfono", example = "test@test.com", required = true)
    private String userCreated;

    @Column(name = "POE_USER_UPDATED", nullable = false, columnDefinition = "VARCHAR(100)")
    @Schema(description = "Usuario quien actualizó el teléfono", example = "test@test.com", required = true)
    private String userUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "POE_DATE_CREATED", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    @Schema(description = "Fecha de cuando creó el teléfono", example = "20-02-2019T07:00:00Z")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "POE_DATE_UPDATED", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @Schema(description = "Fecha de cuando actualizó el teléfono", example = "20-02-2020T07:00:00Z")
    private Date dateUpdated;
}
