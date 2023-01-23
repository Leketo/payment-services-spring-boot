package py.com.leketo.paymentServices.model.transaccion.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import py.com.leketo.paymentServices.model.cuenta.entity.Cuenta;
import py.com.leketo.paymentServices.model.deuda.entity.Deuda;
import py.com.leketo.paymentServices.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private Deuda deuda;
    private User usuario;
    private Cuenta cuenta;
    private BigDecimal monto;
    private LocalDate fecha;
    private String nroDocumentoTitular;
}
