package py.com.leketo.paymentServices.model.cuenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import py.com.leketo.paymentServices.model.cuenta.entity.Cuenta;
import py.com.leketo.paymentServices.model.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    @Query(value = "SELECT c FROM Cuenta c where c.id = ?1")
    Optional<Cuenta> findById(Integer id);

    @Query(value = "SELECT c FROM Cuenta c where c.usuario = ?1")
    List<Cuenta> findByUserId(User user);

    @Modifying
    @Query(value = "UPDATE Cuenta c set c.saldo = :saldo WHERE c.id = :id")
    void updateBalance(@Param("saldo") BigDecimal saldo, @Param("id") Integer id);
}
