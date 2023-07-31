package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.Dto.Request.SucursalRequestDTO;
import Aceleracion.AppBancaria.Entidades.Sucursal;
import Aceleracion.AppBancaria.Mapper.SucursalMapper;
import Aceleracion.AppBancaria.Mapper.SucursalMapperImpl;
import Aceleracion.AppBancaria.Repositorios.RepositorioBanco;
import Aceleracion.AppBancaria.Repositorios.RepositorioSucursal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ServicioSucursal {
    private ServicioBanco servBanco;
    private RepositorioSucursal repoSucursal;
    //private RepositorioBanco repoBanco;

    public ServicioSucursal(ServicioBanco servBanco,RepositorioSucursal repoSucursal) {
        this.servBanco = servBanco;
        this.repoSucursal = repoSucursal;
    }
    @Transactional
    public void crearSucursal(SucursalRequestDTO sucursalRequestDTO) throws Exception {
        SucursalMapper mapper = new SucursalMapperImpl();
        Sucursal sucursal = mapper.sucursalRequestDtoToSucursal(sucursalRequestDTO);
        sucursal.setBanco(servBanco.buscarBanco(sucursalRequestDTO.getIdBanco()));
        repoSucursal.save(sucursal);


    }
    public Sucursal buscarSucursal(Long sucursalId) throws Exception {
        Optional<Sucursal>bD = repoSucursal.findById(sucursalId);
        if(!bD.isPresent()){
            throw new Exception("No Se encontro la sucursal");
        }else{
            Sucursal sucursal = bD.get();
            return sucursal;
        }

    }
}
