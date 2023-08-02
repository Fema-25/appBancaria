package Aceleracion.AppBancaria.Repositorios;

import Aceleracion.AppBancaria.Entidades.CuentaCorriente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioCuentaCorriente extends JpaRepository<CuentaCorriente,Long> {
}
