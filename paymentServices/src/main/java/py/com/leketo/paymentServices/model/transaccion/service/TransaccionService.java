package py.com.leketo.paymentServices.model.transaccion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.com.leketo.paymentServices.exceptions.custom.*;
import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;
import py.com.leketo.paymentServices.model.auth.AuthenticationService;
import py.com.leketo.paymentServices.model.auth.http.BeanUserResponse;
import py.com.leketo.paymentServices.model.cuenta.entity.Cuenta;
import py.com.leketo.paymentServices.model.cuenta.http.BeanCuentaResponse;
import py.com.leketo.paymentServices.model.cuenta.http.UpdateBalanceRequest;
import py.com.leketo.paymentServices.model.cuenta.service.CuentaService;
import py.com.leketo.paymentServices.model.deuda.entity.Deuda;
import py.com.leketo.paymentServices.model.deuda.http.BeanDeudaResponse;
import py.com.leketo.paymentServices.model.deuda.service.DeudaService;
import py.com.leketo.paymentServices.model.transaccion.entity.Transaccion;
import py.com.leketo.paymentServices.model.transaccion.http.BeanTransaccionResponse;
import py.com.leketo.paymentServices.model.transaccion.http.ConfirmarPago;
import py.com.leketo.paymentServices.model.transaccion.http.ListTransaccionResponse;
import py.com.leketo.paymentServices.model.transaccion.http.RegisterRequest;
import py.com.leketo.paymentServices.model.transaccion.repository.TransaccionRepository;
import py.com.leketo.paymentServices.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TransaccionService {

    private final TransaccionRepository repository;
    private final AuthenticationService authenticationService;
    private final CuentaService cuentaService;
    private final DeudaService deudaService;


    /**
     * Registra una nueva deuda
     * @param request
     * @return
     * @throws APIException
     */
    public BeanTransaccionResponse registrarTransaccion(RegisterRequest request) throws APIException {
        if (request == null)
            throw new RegisterPagoNotNullException(APIExceptionType.APPLICATION, "0002", "Parametro de registro de pago no puede ser nulo");

        User user = request.getUsuario();
        if (user == null)
            throw new UserNotNullException(APIExceptionType.APPLICATION, "0002", "Usuario no puede ser nulo");


        //VALIDACION SECCION - CUENTA
        if(request.getCuenta() == null)
            throw new AccountNotNullException(APIExceptionType.APPLICATION, "0002", "Cuenta no se encuentra en la transaccion");

        Cuenta cuenta = request.getCuenta();
        BeanCuentaResponse beanCuentaResponse = cuentaService.findById(cuenta.getId());
        cuenta = beanCuentaResponse.getCuenta();

        //VALIDACION SECCION - DEUDA
        if(request.getDeuda() == null)
            throw new DeudaNotFoundException(APIExceptionType.APPLICATION, "0002", "Deuda no se encuentra en la transaccion");
        Deuda deuda = request.getDeuda();
        BeanDeudaResponse beanDeudaResponse = deudaService.findById(deuda.getId());
        deuda = beanDeudaResponse.getDeuda();

        if ("CANCELADO".equals(deuda.getEstado()))
            throw new DebsSuccessException(APIExceptionType.APPLICATION, "0010", "Deuda ya fue cancelada");


        //VALIDACION DE MONTOS
        BigDecimal montoPagar = request.getMonto();
        BigDecimal saldoCuenta = cuenta.getSaldo();
        BigDecimal montoDeuda = deuda.getMontoDeudaTotal();

        if (montoPagar.compareTo(saldoCuenta) > 0)
            throw new InsufficientFundException(APIExceptionType.APPLICATION, "0002", "No posee saldo suficiente para realizar el pago");

        if (montoPagar.compareTo(montoDeuda) != 0)
            throw new AmountExceededException(APIExceptionType.APPLICATION, "0002", "Debe ingresar el monto de la deuda, ni mas ni menos");

        var transaccion = Transaccion.builder()
                .cuenta(request.getCuenta())
                .deuda(request.getDeuda())
                .nroDocumentoTitular(request.getNroDocumentoTitular())
                .estado("PENDIENTE")
                .monto(request.getMonto())
                .usuario(request.getUsuario())
                .fecha(LocalDate.now())
                .build();
        repository.save(transaccion);
        return BeanTransaccionResponse.builder().transaccion(transaccion).build();
    }

    public ListTransaccionResponse findByDateRange(LocalDate begin, LocalDate end, Integer idUser){
        if (idUser == null)
            throw new UserNotNullException(APIExceptionType.APPLICATION, "0002", "Usuario no puede ser nulo");
        BeanUserResponse beanUserResponse = authenticationService.findById(idUser);

        if (begin == null && end == null)
            throw new DateRangeNotNullException(APIExceptionType.APPLICATION, "0002", "Parametro de fecha no puede ser nulo");
        List<Transaccion> transaccions = repository.findByDateRange(begin, end, beanUserResponse.getUser());
        return ListTransaccionResponse.builder().transaccions(transaccions).build();
    }

    /**
     * Obtiene una transaccion por el Id
     * @param id
     * @return
     */
    public BeanTransaccionResponse findById(Integer id){
        Transaccion transaccion = repository.findById(id).orElse(null);
        return BeanTransaccionResponse.builder().transaccion(transaccion).build();
    }


    public BeanTransaccionResponse confirmarPago(ConfirmarPago confirmarPago, Integer idTransaccion){
        if (confirmarPago == null)
            throw new ConfirmPagoNotNullException(APIExceptionType.APPLICATION, "0002", "Parametro de confirmacion de pago no puede ser nulo");
        String pin = confirmarPago.getPin();
        if (pin == null)
            throw new ConfirmPagoNotNullException(APIExceptionType.APPLICATION, "0010", "Pin transacciona no puede ser nulo");

        BeanTransaccionResponse beanTransaccionResponse = findById(idTransaccion);

        //VALIDACION SECCION - TRANSACCION
        if (beanTransaccionResponse == null || beanTransaccionResponse.getTransaccion() == null)
            throw new TransactionNotFoundException(APIExceptionType.APPLICATION, "0010", "Transaccion no encontrada");
        Transaccion transaccion = beanTransaccionResponse.getTransaccion();
        if ("CONFIRMADO".equals(transaccion.getEstado()))
                throw new TransactionSuccessException(APIExceptionType.APPLICATION, "0010", "Transaccion ya fue confirmada");

        transaccion.setEstado("CONFIRMADO");

        //VALIDACION SECCION - USUARIO
        User user = beanTransaccionResponse.getTransaccion().getUsuario();
        if (user == null)
            throw new UserNotNullException(APIExceptionType.APPLICATION, "0002", "Usuario no se encuentra en la transaccion");
        BeanUserResponse beanUserResponse = authenticationService.findById(user.getId());
        user = beanUserResponse.getUser();

        //VALIDACION SECCION - CUENTA
        if(transaccion.getCuenta() == null)
            throw new AccountNotNullException(APIExceptionType.APPLICATION, "0002", "Cuenta no se encuentra en la transaccion");

        Cuenta cuenta = transaccion.getCuenta();
        BeanCuentaResponse beanCuentaResponse = cuentaService.findById(cuenta.getId());
        cuenta = beanCuentaResponse.getCuenta();

        //VALIDACION SECCION - DEUDA
        if(transaccion.getDeuda() == null)
            throw new DeudaNotFoundException(APIExceptionType.APPLICATION, "0002", "Deuda no se encuentra en la transaccion");
        Deuda deuda = transaccion.getDeuda();
        BeanDeudaResponse beanDeudaResponse = deudaService.findById(deuda.getId());
        deuda = beanDeudaResponse.getDeuda();

        //VALIDACION DE MONTOS
        BigDecimal montoPagar = transaccion.getMonto();
        BigDecimal saldoCuenta = cuenta.getSaldo();
        BigDecimal montoDeuda = deuda.getMontoDeudaTotal();

        if (montoPagar.compareTo(saldoCuenta) > 0)
             throw new InsufficientFundException(APIExceptionType.APPLICATION, "0002", "No posee saldo suficiente para realizar el pago");

        if (montoPagar.compareTo(montoDeuda) != 0)
            throw new AmountExceededException(APIExceptionType.APPLICATION, "0002", "Debe ingresar el monto de la deuda, ni mas ni menos");

        //VALIDACION DE PIN
        if(!pin.equals(user.getPin()))
            throw new PinIncorrectException(APIExceptionType.APPLICATION, "0002", "Pin incorrecto");

        BigDecimal nuevoSaldoCuenta = saldoCuenta.subtract(montoPagar);
        UpdateBalanceRequest saldoBuilder = UpdateBalanceRequest.builder().saldo(nuevoSaldoCuenta).build();

        //cancelar la deuda
        deudaService.cancelarDeuda(montoPagar, deuda.getId());
        cuentaService.updateBalance(saldoBuilder, cuenta.getId());
        repository.updateStatus(transaccion.getEstado(), idTransaccion);
        return BeanTransaccionResponse.builder().transaccion(transaccion).build();
    }
}
