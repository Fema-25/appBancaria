package Aceleracion.AppBancaria.Controladores;

import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.ClienteResponseDTO;
import Aceleracion.AppBancaria.Servicios.ServicioCliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ControladorCliente {
    private final ServicioCliente servCliente;

    public ControladorCliente(ServicioCliente servCliente){
        this.servCliente = servCliente;
    }

    @PostMapping("/registar")
    public ResponseEntity<ClienteResponseDTO>registrar(@ResponseBody ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO usuarioCreado;
        try {

        }

    }

}
