package py.com.leketo.paymentServices.model.deuda.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.leketo.paymentServices.exceptions.errors.APIException;
import py.com.leketo.paymentServices.model.deuda.http.BeanDeudaResponse;
import py.com.leketo.paymentServices.model.deuda.http.ListDeudasResponse;
import py.com.leketo.paymentServices.model.deuda.http.RegisterRequest;
import py.com.leketo.paymentServices.model.deuda.service.DeudaService;

@RestController
@RequestMapping("/api/v1/deudas")
@RequiredArgsConstructor
public class DeudaController {


    private final DeudaService service;

    @GetMapping("/{id}/find_by_id")
    public ResponseEntity<BeanDeudaResponse> findById(@PathVariable("id") Integer id) throws APIException{
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/{id_servicio}/find_by_numero_referencia/{numero_referencia}")
    public ResponseEntity<ListDeudasResponse> findByNumeroReferencia(
            @PathVariable("numero_referencia") String numeroReferencia,
            @PathVariable("id_servicio") Integer idServicio
      ) throws APIException {
        return ResponseEntity.ok(service.findByNumeroReferencia(numeroReferencia, idServicio));
    }

    @PostMapping("/register")
    public ResponseEntity<BeanDeudaResponse> register(
            @RequestBody RegisterRequest request
    ) throws APIException {
        return ResponseEntity.ok(service.register(request));
    }

}
