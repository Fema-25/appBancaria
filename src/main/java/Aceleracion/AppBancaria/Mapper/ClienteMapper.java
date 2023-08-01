package Aceleracion.AppBancaria.Mapper;

import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestActualizarDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.ClienteResponseActulizarDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.ClienteResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
    void actualizarDatosDtoToClinte(ClienteRequestActualizarDTO clienteRequestActualizarDTO, @MappingTarget Cliente cliente);

    ClienteResponseActulizarDTO clienteToClienteResposeActualizarDTO(Cliente cliente);
    ClienteResponseDTO clienteToClienteResponseDto(Cliente cliente);
    Cliente clienteResponseDtoToCliente(ClienteRequestDTO clienteResponseDTO);
}
