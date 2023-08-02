package Aceleracion.AppBancaria.Mapper;

import Aceleracion.AppBancaria.Entidades.Banco;
import Aceleracion.AppBancaria.Entidades.Dto.Request.BancoRequestDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BancoMapper {
    Banco bancoRequestDtoToBanco(BancoRequestDTO bancoRequestDTO);
}
