package py.com.leketo.paymentServices.model.servicios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import py.com.leketo.paymentServices.model.servicios.entity.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServiciosRepository  extends JpaRepository<Servicio, Integer> {

    @Query(value = "SELECT s FROM Servicio s where s.id = ?1")
    Optional<Servicio> findById(Integer id);

    @Query(value = "SELECT s FROM Servicio s where upper(s.nombre) like %:nombre%")
    List<Servicio> findByNombre(String nombre);
}