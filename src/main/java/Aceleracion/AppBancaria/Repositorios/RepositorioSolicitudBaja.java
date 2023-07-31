package Aceleracion.AppBancaria.Repositorios;

import Aceleracion.AppBancaria.Entidades.SolicitudBaja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioSolicitudBaja extends JpaRepository<SolicitudBaja,Long> {
}
