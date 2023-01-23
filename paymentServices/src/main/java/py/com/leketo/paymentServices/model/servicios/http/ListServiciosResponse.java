package py.com.leketo.paymentServices.model.servicios.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import py.com.leketo.paymentServices.model.servicios.entity.Servicio;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListServiciosResponse {
   List<Servicio> servicios;
}
