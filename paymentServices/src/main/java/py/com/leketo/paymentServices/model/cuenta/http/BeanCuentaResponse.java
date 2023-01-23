package py.com.leketo.paymentServices.model.cuenta.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import py.com.leketo.paymentServices.model.cuenta.entity.Cuenta;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeanCuentaResponse {
    Cuenta cuenta;
}
