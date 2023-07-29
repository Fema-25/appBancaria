package Aceleracion.AppBancaria.Mapper;

import Aceleracion.AppBancaria.Entidades.CajaDeAhorro;
import Aceleracion.AppBancaria.Entidades.Dto.Response.CajaAhorroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface CajaAhorroMapper {
    List<CajaAhorroDTO> listCajaDeAhorroToListCajaAhorroDTO(List<CajaDeAhorro> cajaDeAhorro);
    CajaDeAhorro cajaAhorroDtoToCajaAhorroDeAhorro(CajaAhorroDTO cajaAhorroDTO);
}
