package py.com.leketo.paymentServices.model.cuenta.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBalanceRequest {
    @NonNull
    private BigDecimal saldo;
}
