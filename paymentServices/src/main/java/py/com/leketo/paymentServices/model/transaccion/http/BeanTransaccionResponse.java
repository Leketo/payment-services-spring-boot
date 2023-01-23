package py.com.leketo.paymentServices.model.transaccion.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import py.com.leketo.paymentServices.model.transaccion.entity.Transaccion;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeanTransaccionResponse {
    Transaccion transaccion;
}
