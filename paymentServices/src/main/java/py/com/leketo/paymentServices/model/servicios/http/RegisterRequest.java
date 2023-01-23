package py.com.leketo.paymentServices.model.servicios.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import py.com.leketo.paymentServices.model.servicios.pojo.TipoServicio;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nombre;
    private TipoServicio tipoServicio;
    private String codigoComercio;
    private String logo;
}
