package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.Banco;
import Aceleracion.AppBancaria.Entidades.Dto.Request.BancoRequestDTO;
import Aceleracion.AppBancaria.Mapper.BancoMapper;
import Aceleracion.AppBancaria.Mapper.BancoMapperImpl;
import Aceleracion.AppBancaria.Repositorios.RepositorioBanco;
import org.springframework.stereotype.Service;

@Service
public class ServicioBanco {
    private RepositorioBanco repoBanco;

    public ServicioBanco(RepositorioBanco repoBanco) {
        this.repoBanco = repoBanco;
    }

    public void crearBanco(BancoRequestDTO bancoRequestDTO){
        BancoMapper mapper = new BancoMapperImpl();
        Banco banco = mapper.bancoRequestDtoToBanco(bancoRequestDTO);
        repoBanco.save(banco);
    }
}
