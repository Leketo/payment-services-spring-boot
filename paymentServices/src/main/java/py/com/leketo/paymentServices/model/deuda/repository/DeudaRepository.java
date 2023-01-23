package py.com.leketo.paymentServices.model.deuda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import py.com.leketo.paymentServices.model.deuda.entity.Deuda;
import py.com.leketo.paymentServices.model.servicios.entity.Servicio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DeudaRepository extends JpaRepository<Deuda, Integer> {

    @Query(value = "SELECT d FROM Deuda d where d.id = ?1")
    Optional<Deuda> findById(Integer id);

    @Query(value = "SELECT d FROM Deuda d where d.numeroReferenciaComprobante = ?1 and d.servicio = ?2")
    List<Deuda> findByNroReferencia(String numeroReferencia, Servicio servicio);

    @Modifying
    @Query(value = "UPDATE Deuda d set d.montoAbonado = :montoAbonado, d.estado = :estado, d.saldo = 0 WHERE d.id = :id")
    void cancelarDeuda(
            @Param("montoAbonado") BigDecimal montoAbonado,
            @Param("estado") String estado,
            @Param("id") Integer id);

}