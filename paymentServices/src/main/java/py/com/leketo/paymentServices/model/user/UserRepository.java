package py.com.leketo.paymentServices.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import py.com.leketo.paymentServices.model.servicios.entity.Servicio;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    @Query(value = "SELECT u FROM User u where u.id = ?1")
    Optional<User> findById(Integer id);
}
