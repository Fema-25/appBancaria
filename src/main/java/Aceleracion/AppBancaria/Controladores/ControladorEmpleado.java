package Aceleracion.AppBancaria.Controladores;

import Aceleracion.AppBancaria.Entidades.Dto.Request.EmpleadoRequestDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.SolicitudBajaResponseDTO;
import Aceleracion.AppBancaria.Servicios.ServicioEmpleado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleado")
public class ControladorEmpleado {
    private ServicioEmpleado servicioEmpleado;

    public ControladorEmpleado(ServicioEmpleado servicioEmpleado) {
        this.servicioEmpleado = servicioEmpleado;
    }
    @PostMapping("/crearEmpleado")
    public ResponseEntity<String> crearEmpleado(@RequestBody EmpleadoRequestDTO empleadoRequestDTO) {
        try {
            servicioEmpleado.crearEmpleado(empleadoRequestDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Se creo el empleado correctamente");
    }

    public void actualizarDatos() {

    }
    @GetMapping("/listarSolicitudesBaja/{id}")
    public ResponseEntity<?> listaDeSolicitudesBaja(@PathVariable("id") long idSucursal) {
        List<SolicitudBajaResponseDTO> lista;
        try {
            lista = servicioEmpleado.listarSolicitudesBaja(idSucursal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar los datos");
        }
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }
    @PostMapping("/darDeBaja/{idCliente}/{idBaja}")
    public ResponseEntity<?>darDeBaja(@PathVariable("idCliente") long idCliente, @PathVariable("idBaja") long idBaja){
        try {
            servicioEmpleado.darDebaja(idCliente,idBaja);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("El usuario fue dado de baja");
    }
    @PutMapping("/aprobarCuentaCorriente/{idCliente}")
    public ResponseEntity<String> aprobarCuentaCorriente(@PathVariable("idCliente") long idCliente){
        try {
            servicioEmpleado.AprobarCuentaCorriente(idCliente);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Cuenta Corriente Creada");
    }

}
