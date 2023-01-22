package py.com.leketo.paymentServices.model.servicios;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.com.leketo.paymentServices.model.auth.AuthenticationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/servicios")
@RequiredArgsConstructor
public class ServiciosController {

    private final ServiciosService service;

    @GetMapping("/find_all")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @GetMapping("/{estado}/find_by_status")
    public ResponseEntity<ServiciosResponse> findByStatus(@PathVariable("estado") String estado) {
        return ResponseEntity.ok(service.findByEstado(estado));
    }
}
