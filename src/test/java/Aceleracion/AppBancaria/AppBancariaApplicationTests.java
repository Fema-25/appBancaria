package Aceleracion.AppBancaria;

import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestDTO;
import Aceleracion.AppBancaria.Repositorios.RepositorioCliente;
import Aceleracion.AppBancaria.Repositorios.RepositorioSolicitudBaja;
import Aceleracion.AppBancaria.Repositorios.RepositorioSolicitudCuentaCorriente;
import Aceleracion.AppBancaria.Servicios.ServicioCajaAhorro;
import Aceleracion.AppBancaria.Servicios.ServicioCliente;
import Aceleracion.AppBancaria.Servicios.ServicioSucursal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AppBancariaApplicationTests {

	@Test
	void contextLoads() {
	}

}
