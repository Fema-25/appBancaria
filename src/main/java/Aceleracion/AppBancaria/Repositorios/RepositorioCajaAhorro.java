package Aceleracion.AppBancaria.Repositorios;

import Aceleracion.AppBancaria.Entidades.CajaDeAhorro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositorioCajaAhorro extends JpaRepository<CajaDeAhorro,Long> {
    Optional<CajaDeAhorro> findByCbu(String cbu);
}
