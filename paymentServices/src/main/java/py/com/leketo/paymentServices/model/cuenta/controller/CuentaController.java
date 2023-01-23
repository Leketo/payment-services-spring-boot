package py.com.leketo.paymentServices.model.cuenta.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.leketo.paymentServices.exceptions.errors.APIException;
import py.com.leketo.paymentServices.model.cuenta.http.BeanCuentaResponse;
import py.com.leketo.paymentServices.model.cuenta.http.ListCuentasResponse;
import py.com.leketo.paymentServices.model.cuenta.http.RegisterRequest;
import py.com.leketo.paymentServices.model.cuenta.http.UpdateBalanceRequest;
import py.com.leketo.paymentServices.model.cuenta.service.CuentaService;

@RestController
@RequestMapping("/api/v1/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService  service;

    @GetMapping("/find_all")
    public ResponseEntity<ListCuentasResponse> findByAll() throws APIException {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}/find_by_id")
    public ResponseEntity<BeanCuentaResponse> findById(@PathVariable("id") Integer id) throws APIException{
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/{id}/find_by_user_id")
    public ResponseEntity<ListCuentasResponse> findByUserId(@PathVariable("id") Integer id) throws APIException{
        return ResponseEntity.ok(service.findByUserId(id));
    }

    @PostMapping("/register")
    public ResponseEntity<BeanCuentaResponse> register(
            @RequestBody RegisterRequest request
    ) throws APIException {
        return ResponseEntity.ok(service.register(request));
    }

    @PutMapping("/{id}/update/balance")
    public ResponseEntity<BeanCuentaResponse> updateBalance(
            @RequestBody UpdateBalanceRequest request,
            @PathVariable("id") Integer id
    ) throws APIException {
        return ResponseEntity.ok(service.updateBalance(request, id));
    }
}
