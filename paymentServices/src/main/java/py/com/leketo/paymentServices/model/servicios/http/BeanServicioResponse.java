package py.com.leketo.paymentServices.model.servicios.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import py.com.leketo.paymentServices.model.servicios.entity.Servicio;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeanServicioResponse {
    Servicio servicio;
}
