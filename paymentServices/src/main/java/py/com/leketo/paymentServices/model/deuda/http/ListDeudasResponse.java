package py.com.leketo.paymentServices.model.deuda.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import py.com.leketo.paymentServices.model.deuda.entity.Deuda;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListDeudasResponse {
    private List<Deuda> deudas;
}
