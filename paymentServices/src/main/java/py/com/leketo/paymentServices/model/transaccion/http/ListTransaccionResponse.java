package py.com.leketo.paymentServices.model.transaccion.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import py.com.leketo.paymentServices.model.transaccion.entity.Transaccion;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListTransaccionResponse {
    List<Transaccion> transaccions;
}
