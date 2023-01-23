package py.com.leketo.paymentServices.model.deuda.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import py.com.leketo.paymentServices.model.deuda.entity.Deuda;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeanDeudaResponse {
    private Deuda deuda;
}
