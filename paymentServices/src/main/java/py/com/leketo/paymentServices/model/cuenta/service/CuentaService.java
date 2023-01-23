package py.com.leketo.paymentServices.model.cuenta.service;

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
import py.com.leketo.paymentServices.model.cuenta.http.ListCuentasResponse;
import py.com.leketo.paymentServices.model.cuenta.http.RegisterRequest;
import py.com.leketo.paymentServices.model.cuenta.http.UpdateBalanceRequest;
import py.com.leketo.paymentServices.model.cuenta.repository.CuentaRepository;
import py.com.leketo.paymentServices.model.user.User;


import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CuentaService {

    private final CuentaRepository repository;
    private final AuthenticationService authenticationService;

    public BeanCuentaResponse register(RegisterRequest request) throws APIException {
        User user = request.getUsuario();
        if (user == null)
            throw new UserNotNullException(APIExceptionType.APPLICATION, "0002", "Usuario no puede ser nulo");

        BeanUserResponse userReload = authenticationService.findById(user.getId());

        var cuenta = Cuenta.builder()
                        .nombre(request.getNombre())
                        .saldo(request.getSaldo())
                        .usuario(userReload.getUser())
                        .build();
        repository.save(cuenta);
        return BeanCuentaResponse.builder().cuenta(cuenta).build();
    }

    public ListCuentasResponse findAll(){
        List<Cuenta> cuentas = repository.findAll();
        return ListCuentasResponse.builder().cuentas(cuentas).build();
    }

    /**
     * Obtiene un cuenta por el Id
     * @param id
     * @return
     */
    public BeanCuentaResponse findById(Integer id){
        Cuenta cuenta = repository.findById(id).orElse(null);
        return BeanCuentaResponse.builder().cuenta(cuenta).build();
    }

    /**
     * Obtiene un servicio por el user id
     * @param id
     * @return
     */
    public ListCuentasResponse findByUserId(Integer id){
        BeanUserResponse userReload = authenticationService.findById(id);
        List<Cuenta> cuentas = repository.findByUserId(userReload.getUser());
        return ListCuentasResponse.builder().cuentas(cuentas).build();
    }



    /**
     * Actualizar saldo de usuario al realizar un pago
     * @param updateRequest
     * @return
     */
    public BeanCuentaResponse updateBalance(UpdateBalanceRequest updateRequest, Integer id){
        if(id == null)
            throw new AccountNotNullException(APIExceptionType.APPLICATION, "0003", "Cuenta no puede ser nulo");
        BeanCuentaResponse cuentaReload = findById(id);
        if(cuentaReload == null)
            throw new AccountNotFoundException(APIExceptionType.APPLICATION, "0004", "Cuenta no existe");
        if(updateRequest == null)
            throw new UpdateNotNullException(APIExceptionType.APPLICATION, "0004", "Datos para actualizacion de cuenta no puede ser nulo");
        BigDecimal saldo = updateRequest.getSaldo();
        if(saldo == null)
            throw new BalanceNotNullException(APIExceptionType.APPLICATION, "0004", "Saldo de la cuenta no puede ser nulo");
        repository.updateBalance(saldo, id);
        BeanCuentaResponse beanCuentaResponse = findById(id);
        beanCuentaResponse.getCuenta().setSaldo(saldo);
        return beanCuentaResponse;
    }

}
