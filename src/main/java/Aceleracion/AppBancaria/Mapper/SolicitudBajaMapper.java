package Aceleracion.AppBancaria.Mapper;

import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Entidades.Dto.Request.SolicitudBajaDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.ClienteResponseDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.SolicitudBajaResponseDTO;
import Aceleracion.AppBancaria.Entidades.SolicitudBaja;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SolicitudBajaMapper {
    SolicitudBajaMapper INSTANCE = Mappers.getMapper(SolicitudBajaMapper.class);
    SolicitudBaja solicitudBajaDtoToSolicitudBaja(SolicitudBajaDTO solicitudBajaDTO);
    List<SolicitudBajaResponseDTO> solicitudBajaListToSolicitudBajaResponseDtoList(List<SolicitudBaja> solicitudBaja);
    @Mapping(target = "cliente", source = "cliente")
    List<SolicitudBajaResponseDTO> SolicitudBajaResponseDtoToSolicitudBaja(List<SolicitudBaja> solicitudBaja);
    default ClienteResponseDTO clienteToClienteResponseDto(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return ClienteMapper.INSTANCE.clienteToClienteResponseDto(cliente);
    }
}
