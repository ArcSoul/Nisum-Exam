package cl.com.nisum.ws.technicalexam.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

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
public class PhoneEntity {

    @Id
    @Column(name = "POE_ID", nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "POE_NUMBER", nullable = false, columnDefinition = "VARCHAR(15)")
    private String number;

    @Column(name = "POE_CITY_CODE", nullable = false, columnDefinition = "INT")
    private Integer cityCode;

    @Column(name = "POE_COUNTRY_CODE", nullable = false, columnDefinition = "INT")
    private Integer countryCode;

    @JoinColumn(name = "POE_USR_ID", nullable = false, updatable = false)
    @ManyToOne
    private UserEntity user;

    @Column(name = "POE_USER_CREATED", nullable = false, columnDefinition = "VARCHAR(100)")
    private String userCreated;

    @Column(name = "POE_USER_UPDATED", nullable = false, columnDefinition = "VARCHAR(100)")
    private String userUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "POE_DATE_CREATED", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "POE_DATE_UPDATED", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date dateUpdated;
}
