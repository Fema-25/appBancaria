package Aceleracion.AppBancaria.Mapper;

import Aceleracion.AppBancaria.Entidades.Dto.Request.SolicitudBajaDTO;
import Aceleracion.AppBancaria.Entidades.SolicitudBaja;
import org.mapstruct.Mapper;

@Mapper
public interface SolicitudBajaMapper {
    SolicitudBaja solicitudBajaDtoToSolicitudBaja(SolicitudBajaDTO solicitudBajaDTO);
}
