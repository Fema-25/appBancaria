package Aceleracion.AppBancaria.Controladores;

import Aceleracion.AppBancaria.Entidades.Dto.Request.BancoRequestDTO;
import Aceleracion.AppBancaria.Servicios.ServicioBanco;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banco")
public class ControladorBanco {
    private ServicioBanco servBanco;

    public ControladorBanco(ServicioBanco servBanco) {
        this.servBanco = servBanco;
    }
    @PostMapping("/crearBanco")
    public ResponseEntity<String> crearBanco(@RequestBody BancoRequestDTO bancoRequestDTO){
        try {
            servBanco.crearBanco(bancoRequestDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Banco Creado");

    }
}
