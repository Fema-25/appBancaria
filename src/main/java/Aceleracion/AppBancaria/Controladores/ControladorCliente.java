package Aceleracion.AppBancaria.Controladores;

import Aceleracion.AppBancaria.Servicios.ServicioCliente;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ControladorCliente {
    private final ServicioCliente servCliente;

    public ControladorCliente(ServicioCliente servCliente){
        this.servCliente = servCliente;
    }
    
}
