package Aceleracion.AppBancaria.Controladores;

import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestActualizarDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.ClienteResponseActulizarDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.ClienteResponseDTO;
import Aceleracion.AppBancaria.Servicios.ServicioCliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ControladorCliente {
    private final ServicioCliente servCliente;

    public ControladorCliente(ServicioCliente servCliente){
        this.servCliente = servCliente;
    }

    @PostMapping("/registar")
    public ResponseEntity<ClienteResponseDTO>registrar(@RequestBody ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO usuarioCreado;
        try {
            usuarioCreado = servCliente.crearCliente(clienteRequestDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioCreado);
    }

    @PutMapping("/actualizarDatos")
    public ResponseEntity<ClienteResponseActulizarDTO>actualizarDatosPersonales(@RequestBody ClienteRequestActualizarDTO clienteRequestActualizarDTO){
        ClienteResponseActulizarDTO usuarioActualizado;
        try {
           usuarioActualizado = servCliente.actualizarDatos(clienteRequestActualizarDTO);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioActualizado);
    }

}
