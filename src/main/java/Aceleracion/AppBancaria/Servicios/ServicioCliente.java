package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestActualizarDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.ClienteResponseActulizarDTO;
import Aceleracion.AppBancaria.Mapper.ClienteMapper;
import Aceleracion.AppBancaria.Mapper.ClienteMapperImpl;
import Aceleracion.AppBancaria.Repositorios.RepositorioCliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import javax.validation.*;

import java.util.Optional;
@Service
@Validated
public class ServicioCliente {


    private final RepositorioCliente repoCliente;


    public ServicioCliente(RepositorioCliente repoClinte) {
        this.repoCliente = repoClinte;


    }
    public void crearCliente(@Valid ClienteRequestDTO clienteDto)  throws Exception{
        ModelMapper modelMapper = new ModelMapper();
        Optional<Cliente> cliente = repoCliente.findByDni(clienteDto.getDni());
        if(cliente.isPresent()){
            throw new Exception("El dni con el que esta ingreasndo ya esta registrado");
        }
        Cliente persiste = modelMapper.map(clienteDto,Cliente.class);

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


}
