package Aceleracion.AppBancaria.Repositorios;

import Aceleracion.AppBancaria.Entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioEmpleado extends JpaRepository<Empleado,Long > {
    Optional<Empleado> findByDni(String dni);
}
