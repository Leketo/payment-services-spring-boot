package py.com.leketo.paymentServices.model.deuda.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import py.com.leketo.paymentServices.model.servicios.entity.Servicio;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String numeroReferenciaComprobante;
    private BigDecimal montoDeudaTotal;
    private Servicio servicio;
    private String nroDocumentoTitular;
    private LocalDate fechaVencimiento;

}
