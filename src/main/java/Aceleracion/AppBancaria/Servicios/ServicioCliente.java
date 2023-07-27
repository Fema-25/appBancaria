package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.ClienteResponseDTO;
import org.modelmapper.ModelMapper;

public class ServicioCliente {

    public ClienteResponseDTO crearCliente(ClienteRequestDTO clienteDto){
        ModelMapper modelMapper = new ModelMapper();
        Cliente cliente = modelMapper.map(clienteDto, Cliente.class);

    }
}
