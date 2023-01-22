package py.com.leketo.paymentServices.model.servicios;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiciosService {

    private final ServiciosRepository repository;

    public ServiciosResponse findByEstado(String estado){
        List<Servicio> servicios = repository.findByEstado(estado);
        return ServiciosResponse.builder().servicios(servicios).build();
    }

}
