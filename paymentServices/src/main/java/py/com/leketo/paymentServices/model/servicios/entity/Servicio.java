package py.com.leketo.paymentServices.model.servicios.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import py.com.leketo.paymentServices.model.cuenta.entity.Cuenta;
import py.com.leketo.paymentServices.model.deuda.entity.Deuda;
import py.com.leketo.paymentServices.model.servicios.pojo.TipoServicio;

import java.util.Set;

@JsonIgnoreProperties(value = { "deudas"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_servicio")
@Setter
@Getter
public class Servicio {
    @Id
    @GeneratedValue
    private Integer id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private TipoServicio tipoServicio;
    private String codigoComercio;
    private String estado;
    private String logo;

    @OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Deuda> deudas;
}
