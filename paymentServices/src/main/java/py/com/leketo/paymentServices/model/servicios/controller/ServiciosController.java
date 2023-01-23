package py.com.leketo.paymentServices.model.servicios.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.leketo.paymentServices.model.servicios.http.BeanServicioResponse;
import py.com.leketo.paymentServices.model.servicios.http.ListServiciosResponse;
import py.com.leketo.paymentServices.model.servicios.http.RegisterRequest;
import py.com.leketo.paymentServices.model.servicios.service.ServiciosService;

@RestController
@RequestMapping("/api/v1/servicios")
@RequiredArgsConstructor
public class ServiciosController {

    private final ServiciosService service;

    @GetMapping("/find_all")
    public ResponseEntity<ListServiciosResponse> findByAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}/find_by_id")
    public ResponseEntity<BeanServicioResponse> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/{nombre}/find_by_nombre")
    public ResponseEntity<ListServiciosResponse> findByNombre(@PathVariable("nombre") String nombre) {
        return ResponseEntity.ok(service.findByNombre(nombre));
    }

    @PostMapping("/register")
    public ResponseEntity<BeanServicioResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
}
