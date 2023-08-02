package Aceleracion.AppBancaria.Mapper;

import Aceleracion.AppBancaria.Entidades.Dto.Request.SucursalRequestDTO;
import Aceleracion.AppBancaria.Entidades.Sucursal;
import org.mapstruct.Mapper;

@Mapper
public interface SucursalMapper {
    Sucursal sucursalRequestDtoToSucursal(SucursalRequestDTO sucursalRequestDTO);
}
