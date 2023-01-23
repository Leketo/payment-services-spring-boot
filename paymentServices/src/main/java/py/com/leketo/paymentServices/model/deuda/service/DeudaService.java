package py.com.leketo.paymentServices.model.deuda.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.com.leketo.paymentServices.exceptions.custom.*;
import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;
import py.com.leketo.paymentServices.model.auth.AuthenticationService;
import py.com.leketo.paymentServices.model.deuda.entity.Deuda;
import py.com.leketo.paymentServices.model.deuda.http.BeanDeudaResponse;
import py.com.leketo.paymentServices.model.deuda.http.ListDeudasResponse;
import py.com.leketo.paymentServices.model.deuda.http.RegisterRequest;
import py.com.leketo.paymentServices.model.deuda.repository.DeudaRepository;
import py.com.leketo.paymentServices.model.servicios.entity.Servicio;
import py.com.leketo.paymentServices.model.servicios.http.BeanServicioResponse;
import py.com.leketo.paymentServices.model.servicios.service.ServiciosService;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeudaService {

    private final DeudaRepository repository;
    private final AuthenticationService authenticationService;
    private final ServiciosService serviciosService;

    /**
     * Registra una nueva deuda
     * @param request
     * @return
     * @throws APIException
     */
    public BeanDeudaResponse register(RegisterRequest request) throws APIException {
        var deuda = Deuda.builder()
                .numeroReferenciaComprobante(request.getNumeroReferenciaComprobante())
                .montoDeudaTotal(request.getMontoDeudaTotal())
                .montoAbonado(BigDecimal.ZERO)
                .estado("PENDIENTE")
                .saldo(request.getMontoDeudaTotal())
                .servicio(request.getServicio())
                .nroDocumentoTitular(request.getNroDocumentoTitular())
                .fechaVencimiento(request.getFechaVencimiento())
                .build();
        repository.save(deuda);
        return BeanDeudaResponse.builder().deuda(deuda).build();
    }

    /**
     * Obtiene un deuda por el Id
     * @param id
     * @return
     */
    public BeanDeudaResponse findById(Integer id){
        Deuda deuda = repository.findById(id).orElse(null);
        return BeanDeudaResponse.builder().deuda(deuda).build();
    }

    /**
     * Cancela una deuda
     * @param idDeuda
     */
    public void cancelarDeuda(BigDecimal monto, Integer idDeuda){
        repository.cancelarDeuda(monto, "CANCELADO", idDeuda);

    }

    /**
     * Obtiene un la deuda por el numero de referencia
     * Ejemplo:
     * 1- el numero de referecia de una factura de ANDE es el NIS
     * 2- el numero de referencia de un prestamo seria el nro de CI
     * @param nroReferencia: Numero identificador de la deuda
     * @param idServicio: Al tipo de servicio que corresponde (Ande, Essap, Tigo, etc)
     * @return
     */
    public ListDeudasResponse findByNumeroReferencia(String nroReferencia, Integer idServicio){
        if (nroReferencia == null)
            throw new NumeroReferenciaNotNullException(APIExceptionType.APPLICATION, "0006", "Numero referencia es requerido");

        if (idServicio == null)
            throw new ServiceNotNullException(APIExceptionType.APPLICATION, "0007", "Servicio es requerido");

        BeanServicioResponse beanServicioResponse = serviciosService.findById(idServicio);

        if(beanServicioResponse == null)
            throw new ServiceNotFoundException(APIExceptionType.APPLICATION, "0008", "Servicio no encontrado por el identificador");

        Servicio servicio = beanServicioResponse.getServicio();
        List<Deuda> deudas = repository.findByNroReferencia(nroReferencia, servicio);

        if (deudas == null || deudas.isEmpty())
            throw new DeudaNotFoundException(APIExceptionType.APPLICATION, "0009", "No se obtuvo deudas para el número de referencia y el servicio");

        return ListDeudasResponse.builder().deudas(deudas).build();
    }

}
