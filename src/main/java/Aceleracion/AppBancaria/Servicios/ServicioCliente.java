package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.CajaDeAhorro;
import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestActualizarDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Request.TranferenciaRequestDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.CajaAhorroDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.ClienteResponseActulizarDTO;
import Aceleracion.AppBancaria.Entidades.Sucursal;
import Aceleracion.AppBancaria.Mapper.CajaAhorroMapper;
import Aceleracion.AppBancaria.Mapper.CajaAhorroMapperImpl;
import Aceleracion.AppBancaria.Mapper.ClienteMapper;
import Aceleracion.AppBancaria.Mapper.ClienteMapperImpl;
import Aceleracion.AppBancaria.Repositorios.RepositorioCajaAhorro;
import Aceleracion.AppBancaria.Repositorios.RepositorioCliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Validated
public class ServicioCliente {


    private final RepositorioCliente repoCliente;
    private ServicioCajaAhorro servCajaAhorro;
    private ServicioSucursal servSucursal;




    public ServicioCliente(RepositorioCliente repoClinte,ServicioCajaAhorro servCajaAhorro,ServicioSucursal servSucursal) {
        this.repoCliente = repoClinte;
        this.servCajaAhorro = servCajaAhorro;
        this.servSucursal = servSucursal;


    }
    @Transactional
    public void crearCliente(@Valid ClienteRequestDTO clienteDto)  throws Exception{
        ModelMapper modelMapper = new ModelMapper();
        Optional<Cliente> cliente = repoCliente.findByDni(clienteDto.getDni());

        if(cliente.isPresent()){
            throw new Exception("El dni con el que esta ingreasndo ya esta registrado");
        }
        Cliente persiste = modelMapper.map(clienteDto,Cliente.class);
        persiste.setSucursal(servSucursal.buscarSucursal(clienteDto.getSucursalId()));
        persiste.setAlta(true);
        repoCliente.save(persiste);


    }

    public ClienteResponseActulizarDTO actualizarDatos(ClienteRequestActualizarDTO clienteRequestActualizarDTO) throws Exception {
            ClienteMapper mapper = new ClienteMapperImpl();
            Optional<Cliente> bD = repoCliente.findById(clienteRequestActualizarDTO.getId());
            if(bD.isPresent()){
                Cliente cliente = bD.get();
                mapper.actualizarDatosDtoToClinte(clienteRequestActualizarDTO,cliente);
                repoCliente.save(cliente);
                return mapper.clienteToClienteResposeActualizarDTO(cliente);
            }
            else{
               throw new Exception("No se pudo encontrar el usuario");
            }
    }
    public void crearCajaDeAhorro(Long id) throws Exception{
        Optional<Cliente> bD = repoCliente.findById(id);
        if(bD.isPresent()){
            Cliente cliente = bD.get();
            List<CajaDeAhorro> cajas = cliente.getCajaDeAhorro();
            cajas.add(servCajaAhorro.crearCajaAhorro(cliente));
            cliente.setCajaDeAhorro(cajas);
            repoCliente.save(cliente);
        }
        else{
            throw new Exception("Error al encotrar el User en la base de datos intentelo mas tarde");
        }
    }
    public List<CajaAhorroDTO>listaCajasAhorro(Long id) throws Exception{
        CajaAhorroMapper cajaAhorroMapper = new CajaAhorroMapperImpl();
        Optional<Cliente> bD = repoCliente.findById(id);
        if(bD.isPresent()){
            Cliente cliente = bD.get();
            return cajaAhorroMapper.listCajaDeAhorroToListCajaAhorroDTO(cliente.getCajaDeAhorro());
        }
        else {
            throw new Exception("Error al encotrar el User en la base de datos intentelo mas tarde");
        }
    }

    public void ingresarDineroCajaAhorro(CajaAhorroDTO cajaAhorroDTO) throws Exception {
            servCajaAhorro.ingresarDineroCajaAhorro(cajaAhorroDTO);
    }

    public void retirarDineroCajaAhorro(CajaAhorroDTO cajaAhorroDTO) throws Exception {
        servCajaAhorro.retirarDineroCajaAhorro(cajaAhorroDTO);
    }

    public void transferenciaCbu(TranferenciaRequestDTO tranferenciaRequestDTO) throws Exception{
        servCajaAhorro.transferenciaCbu(tranferenciaRequestDTO);
    }

}
