package Aceleracion.AppBancaria.Repositorios;

import Aceleracion.AppBancaria.Entidades.SolicitudBaja;
import Aceleracion.AppBancaria.Entidades.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepositorioSolicitudBaja extends JpaRepository<SolicitudBaja,Long> {
    @Query("SELECT sb FROM SolicitudBaja sb JOIN sb.cliente c WHERE c.sucursal = :sucursal")
    List<SolicitudBaja> listarSolicitudBaja(@Param("sucursal") Sucursal sucursal);
    Optional<SolicitudBaja> findById(long iDbaja);
}
