package Aceleracion.AppBancaria.Controladores;

import Aceleracion.AppBancaria.Entidades.Dto.Request.EmpleadoRequestDTO;
import Aceleracion.AppBancaria.Servicios.ServicioEmpleado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void listaDeSolicitudesBaja(){

    }
    public void listaSolicitudesCuentaCorrientes(){

    }

}
