package Aceleracion.AppBancaria.Repositorios;

import Aceleracion.AppBancaria.Entidades.SolicitudCuenteCorriente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioSolicitudCuentaCorriente extends JpaRepository<SolicitudCuenteCorriente,Long> {
}
