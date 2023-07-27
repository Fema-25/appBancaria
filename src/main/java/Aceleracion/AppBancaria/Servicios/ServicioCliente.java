package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.ClienteResponseDTO;
import Aceleracion.AppBancaria.Repositorios.RepositorioCliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ServicioCliente {


    private final RepositorioCliente repoCliente;

    public ServicioCliente(RepositorioCliente repoClinte){
        this.repoCliente = repoClinte;

    }
    public ClienteResponseDTO crearCliente(ClienteRequestDTO clienteDto)  throws Exception{
        ModelMapper modelMapper = new ModelMapper();
        Optional<Cliente> cliente = repoCliente.findByDni(clienteDto.getDni());
        if(cliente.isPresent()){
            throw new Exception("El dni con el que esta ingreasndo ya esta registrado");
        }
        Cliente persiste = modelMapper.map(clienteDto,Cliente.class);
        Cliente respuesta = repoCliente.save(persiste);
        return modelMapper.map(respuesta,ClienteResponseDTO.class);

    }
}
