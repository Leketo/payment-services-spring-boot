package py.com.leketo.paymentServices.model.servicios;

import jakarta.persistence.*;
import lombok.*;

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
}
