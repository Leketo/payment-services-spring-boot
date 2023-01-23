package py.com.leketo.paymentServices.model.transaccion.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import py.com.leketo.paymentServices.model.cuenta.entity.Cuenta;
import py.com.leketo.paymentServices.model.deuda.entity.Deuda;
import py.com.leketo.paymentServices.model.servicios.entity.Servicio;
import py.com.leketo.paymentServices.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_transaccion")
@Setter
@Getter
public class Transaccion {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_deuda")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Deuda deuda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cuenta")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cuenta cuenta;

    private BigDecimal monto;
    private LocalDate fecha;
    private String estado;
    private String nroDocumentoTitular;

}
