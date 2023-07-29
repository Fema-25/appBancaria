package Aceleracion.AppBancaria.Mapper;

import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestActualizarDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.ClienteResponseActulizarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClienteMapper {

    void actualizarDatosDtoToClinte(ClienteRequestActualizarDTO clienteRequestActualizarDTO, @MappingTarget Cliente cliente);

    ClienteResponseActulizarDTO clienteToClienteResposeActualizarDTO(Cliente cliente);
}
