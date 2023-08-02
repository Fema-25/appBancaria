package Aceleracion.AppBancaria.Controladores;

import Aceleracion.AppBancaria.Entidades.Dto.Request.SucursalRequestDTO;
import Aceleracion.AppBancaria.Servicios.ServicioSucursal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sucursal")
public class ControladorSucursal {
    private ServicioSucursal servSucursal;

    public ControladorSucursal(ServicioSucursal servSucursal) {
        this.servSucursal = servSucursal;
    }
    @PostMapping("/crearSucursal")
    public ResponseEntity<String>crearSucursal(@RequestBody SucursalRequestDTO sucursalRequestDTO){
        try {
            servSucursal.crearSucursal(sucursalRequestDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Sucursal Creada Exitosamente");
    }
}
