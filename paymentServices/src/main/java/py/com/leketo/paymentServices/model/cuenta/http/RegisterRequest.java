package py.com.leketo.paymentServices.model.cuenta.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import py.com.leketo.paymentServices.model.user.User;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private User usuario;
    private String nombre;
    private BigDecimal saldo;
}
