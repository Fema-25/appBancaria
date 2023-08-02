package Aceleracion.AppBancaria.Mapper;

import Aceleracion.AppBancaria.Entidades.Dto.Request.EmpleadoRequestDTO;
import Aceleracion.AppBancaria.Entidades.Empleado;
import org.mapstruct.Mapper;

@Mapper
public interface EmpleadoMapper {
    Empleado empleadoRequestDtoToEmpleado(EmpleadoRequestDTO empleadoRequestDTO);
}
