package Aceleracion.AppBancaria.Repositorios;

import Aceleracion.AppBancaria.Entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RepositorioCliente  extends JpaRepository<Cliente,Long> {
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente>findByDni(String dni);
    Optional<Cliente>findById(Long id);
}
