package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.Banco;
import Aceleracion.AppBancaria.Entidades.Dto.Request.BancoRequestDTO;
import Aceleracion.AppBancaria.Entidades.Sucursal;
import Aceleracion.AppBancaria.Mapper.BancoMapper;
import Aceleracion.AppBancaria.Mapper.BancoMapperImpl;
import Aceleracion.AppBancaria.Repositorios.RepositorioBanco;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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

    public Banco buscarBanco(Long idBanco) throws Exception {
        Optional<Banco>bD = repoBanco.findById(idBanco);
        if (!bD.isPresent()){
            throw new Exception("No se encontro el banco");
        }
        else{
            Banco banco = bD.get();
            return banco;
        }

    }
}
