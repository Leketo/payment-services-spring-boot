package py.com.leketo.paymentServices.model.servicios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiciosRepository  extends JpaRepository<Servicio, Integer> {
    @Query(value = "SELECT s FROM Servicio s where s.estado = ?1")
    List<Servicio> findByEstado(String estado);
}