package py.com.leketo.paymentServices.model.cuenta.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import py.com.leketo.paymentServices.model.cuenta.entity.Cuenta;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListCuentasResponse {
    List<Cuenta> cuentas;
}
