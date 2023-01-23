package py.com.leketo.paymentServices.model.transaccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import py.com.leketo.paymentServices.model.transaccion.entity.Transaccion;
import py.com.leketo.paymentServices.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {

    @Query(value = "SELECT t FROM Transaccion t where t.id = ?1")
    Optional<Transaccion> findById(Integer id);

    @Query(value = "SELECT t FROM Transaccion t WHERE t.fecha BETWEEN ?1 AND ?2 AND t.usuario = ?3")
    List<Transaccion> findByDateRange(LocalDate begin, LocalDate end, User user);

    @Modifying
    @Query(value = "UPDATE Transaccion t set t.estado = ?1 WHERE t.id = ?2")
    void updateStatus(@Param("estado") String estado, @Param("id") Integer id);


}
