package py.com.leketo.paymentServices.model.cuenta.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import py.com.leketo.paymentServices.model.transaccion.entity.Transaccion;
import py.com.leketo.paymentServices.model.user.User;

import java.math.BigDecimal;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIgnoreProperties(value = { "transaccions"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_cuenta")
@Setter
@Getter
public class Cuenta {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User usuario;

    @OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Transaccion> transaccions;


    private String nombre;
    private BigDecimal saldo;

}
