package py.com.leketo.paymentServices.model.transaccion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import py.com.leketo.paymentServices.exceptions.errors.APIException;
import py.com.leketo.paymentServices.model.transaccion.http.BeanTransaccionResponse;
import py.com.leketo.paymentServices.model.transaccion.http.ConfirmarPago;
import py.com.leketo.paymentServices.model.transaccion.http.ListTransaccionResponse;
import py.com.leketo.paymentServices.model.transaccion.http.RegisterRequest;
import py.com.leketo.paymentServices.model.transaccion.service.TransaccionService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/transacciones")
@RequiredArgsConstructor
public class TransaccionController {

   private final TransaccionService service;

    @GetMapping("/{user_id}/find_by_date")
    public ResponseEntity<ListTransaccionResponse> findByDateRange(
            Authentication authentication,
            @PathVariable("user_id") Integer userId,
            @RequestParam("begin") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate begin,
            @RequestParam("end") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate end
    ) throws APIException {
        System.out.println("Authentication:::" +authentication.getName());
        return ResponseEntity.ok(service.findByDateRange(begin, end, userId));
    }

    @PostMapping("/registrar-pago")
    public ResponseEntity<BeanTransaccionResponse> registrarTransaccion(
            @RequestBody RegisterRequest request
    ) throws APIException {
        return ResponseEntity.ok(service.registrarTransaccion(request));
    }

    @PutMapping("/{id_transaccion}/confirmar-pago")
    public ResponseEntity<BeanTransaccionResponse> confirmarPago(
            @RequestBody ConfirmarPago confirmarPago,
            @PathVariable("id_transaccion") Integer idTransaccion
    ) throws APIException {
        return ResponseEntity.ok(service.confirmarPago(confirmarPago, idTransaccion));
    }


}
