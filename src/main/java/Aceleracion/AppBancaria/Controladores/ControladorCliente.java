package Aceleracion.AppBancaria.Controladores;

import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestActualizarDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.CajaAhorroDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.ClienteResponseActulizarDTO;
import Aceleracion.AppBancaria.Servicios.ServicioCliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/cliente")
public class ControladorCliente {
    private final ServicioCliente servCliente;

    public ControladorCliente(ServicioCliente servCliente){
        this.servCliente = servCliente;
    }

    @PostMapping("/registar")
    public ResponseEntity<String>registrar( @RequestBody ClienteRequestDTO clienteRequestDTO){

        try {
            servCliente.crearCliente(clienteRequestDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("El usuario se creo correctamente");
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

    //@PostMapping("/solicitud")

    @PutMapping("/crearCajaDeAhorro/{id}")
    public ResponseEntity<String>crearCajaDeAhorro(@PathVariable Long id){
        try {
            servCliente.crearCajaDeAhorro(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Su caja de ahorro fue creada correctamente");
    }

    @GetMapping("/listaCajasAhorro/{id}")
    public ResponseEntity<?>listaCajasAhorro(@PathVariable Long id){
        List<CajaAhorroDTO> lista = new ArrayList<>();
        try {
            lista = servCliente.listaCajasAhorro(id);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }
}
