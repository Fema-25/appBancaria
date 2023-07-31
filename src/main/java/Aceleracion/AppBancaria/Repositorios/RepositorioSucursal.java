package Aceleracion.AppBancaria.Repositorios;

import Aceleracion.AppBancaria.Entidades.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioSucursal extends JpaRepository<Sucursal,Long> {
    Optional<Sucursal> findById(Long id);
}
