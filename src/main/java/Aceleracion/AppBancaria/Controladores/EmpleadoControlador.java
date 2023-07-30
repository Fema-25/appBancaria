package Aceleracion.AppBancaria.Controladores;

import Aceleracion.AppBancaria.Entidades.Dto.Request.EmpleadoRequestDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empleado")
public class EmpleadoControlador {
    public void crearEmpleado(@RequestBody EmpleadoRequestDTO empleadoRequestDTO) {

    }

    public void actualizarDatos() {

    }
    public void listaDeSolicitudesBaja(){

    }
    public void listaSolicitudesCuentaCorrientes(){

    }

}
