package py.com.leketo.paymentServices.model.servicios.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.com.leketo.paymentServices.exceptions.errors.APIException;
import py.com.leketo.paymentServices.model.servicios.http.BeanServicioResponse;
import py.com.leketo.paymentServices.model.servicios.http.ListServiciosResponse;
import py.com.leketo.paymentServices.model.servicios.http.RegisterRequest;
import py.com.leketo.paymentServices.model.servicios.repository.ServiciosRepository;
import py.com.leketo.paymentServices.model.servicios.entity.Servicio;
import py.com.leketo.paymentServices.model.servicios.pojo.Estado;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiciosService {

    private final ServiciosRepository repository;

    public BeanServicioResponse register(RegisterRequest request) throws APIException {
        var servicio = Servicio.builder()
                .nombre(request.getNombre())
                .codigoComercio(request.getCodigoComercio())
                .tipoServicio(request.getTipoServicio())
                .estado(Estado.ACTIVO.toString())
                .logo(request.getLogo())
                .build();
        repository.save(servicio);
        return BeanServicioResponse.builder().servicio(servicio).build();
    }

    public ListServiciosResponse findAll(){
        List<Servicio> servicios = repository.findAll();
        return ListServiciosResponse.builder().servicios(servicios).build();
    }

    /**
     * Obtiene un servicio por el Id
     * @param id
     * @return
     */
    public BeanServicioResponse findById(Integer id){
        Servicio servicio = repository.findById(id).orElse(null);;
        return BeanServicioResponse.builder().servicio(servicio).build();
    }

    /**
     * Obtiene servicios por nombre
     * @param nombre
     * @return
     */
    public ListServiciosResponse findByNombre(String nombre){
        List<Servicio> servicios = repository.findByNombre(nombre);
        return ListServiciosResponse.builder().servicios(servicios).build();
    }

}
