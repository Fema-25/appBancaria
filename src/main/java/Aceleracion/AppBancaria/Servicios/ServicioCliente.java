package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestDto;
import org.modelmapper.ModelMapper;

public class ServicioCliente {
    public void crearCliente(ClienteRequestDto clienteDto){
        ModelMapper modelMapper = new ModelMapper();
        Cliente cliente = modelMapper.map(clienteDto, Cliente.class);

    }
}
