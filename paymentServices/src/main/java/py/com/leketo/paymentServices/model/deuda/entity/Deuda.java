package py.com.leketo.paymentServices.model.deuda.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import py.com.leketo.paymentServices.model.servicios.entity.Servicio;
import py.com.leketo.paymentServices.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_deuda")
@Setter
@Getter
public class Deuda {

    @Id
    @GeneratedValue
    private Integer id;

    private String numeroReferenciaComprobante;
    private BigDecimal montoDeudaTotal;
    private BigDecimal montoAbonado;
    private BigDecimal saldo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_servicio")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Servicio servicio;

    private String nroDocumentoTitular;
    private LocalDate fechaVencimiento;
    private String estado;

}
