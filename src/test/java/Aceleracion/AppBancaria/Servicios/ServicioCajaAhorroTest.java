package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.CajaDeAhorro;
import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Entidades.Dto.Request.TranferenciaRequestDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.CajaAhorroDTO;
import Aceleracion.AppBancaria.Repositorios.RepositorioCajaAhorro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;

@SpringBootTest
class ServicioCajaAhorroTest {
    @Mock
    private RepositorioCajaAhorro repoCajaAhorro;

    @InjectMocks
    private ServicioCajaAhorro servicioCajaAhorro;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); //esto inicializa los mocks
        servicioCajaAhorro = new ServicioCajaAhorro(repoCajaAhorro);

    }



    @Test
    @DisplayName("Test crear caja de ahorro")
    public void testCrearCajaAhorro() {
        Cliente clienteMock = new Cliente();

        CajaDeAhorro cajaDeAhorro = servicioCajaAhorro.crearCajaAhorro(clienteMock);

        assertNotNull(cajaDeAhorro);
        assertNotNull(cajaDeAhorro.getCbu());
        assertEquals(clienteMock, cajaDeAhorro.getCliente());

    }

    @Test
    @DisplayName("Test ingresar dinero en caja de ahorro")

    public void testIngresarDineroCajaAhorro() throws Exception {
        CajaAhorroDTO cajaAhorroDTO = new CajaAhorroDTO();
        cajaAhorroDTO.setId(5L);
        cajaAhorroDTO.setSaldo(BigDecimal.valueOf(1500));

        CajaDeAhorro cajaMock = new CajaDeAhorro();
        cajaMock.setSaldo(BigDecimal.valueOf(600));

        Mockito.when(repoCajaAhorro.findById(cajaAhorroDTO.getId())).thenReturn(Optional.of(cajaMock));

        servicioCajaAhorro.ingresarDineroCajaAhorro(cajaAhorroDTO);

        assertEquals(BigDecimal.valueOf(2100), cajaMock.getSaldo()); //verifico que el saldo sea el que corresponde
        assertTrue(cajaMock.getSaldo().compareTo(BigDecimal.ZERO) >= 0); //verifico que el saldo no sea igual a 0


    }


    @Test
    @DisplayName("Test retirar dinero de caja de ahorro")

    public void testRetirarDineroCajaAhorro() throws Exception {
        CajaAhorroDTO cajaAhorroDTO = new CajaAhorroDTO();
        cajaAhorroDTO.setId(1L);
        cajaAhorroDTO.setSaldo(new BigDecimal(3000));

        CajaDeAhorro mockCaja = new CajaDeAhorro();
        mockCaja.setSaldo(BigDecimal.valueOf(5000));

        Mockito.when(repoCajaAhorro.findById(cajaAhorroDTO.getId())).thenReturn(Optional.of(mockCaja));

        servicioCajaAhorro.retirarDineroCajaAhorro(cajaAhorroDTO);

        assertEquals(BigDecimal.valueOf(2000), mockCaja.getSaldo()); //verifico el saldo final luego de hacer el retiro
    }

    @Test
    @DisplayName("Test transferir dinero entre cuentas")

    public void testTransferenciaCbu() throws Exception {
        TranferenciaRequestDTO requestDTO = new TranferenciaRequestDTO();
        requestDTO.setCbuInicio("cbuInicio");
        requestDTO.setCbuFinal("cbuFinal");
        requestDTO.setMonto(BigDecimal.valueOf(100));

        CajaDeAhorro cajaInicio = new CajaDeAhorro();
        cajaInicio.setSaldo(BigDecimal.valueOf(200));

        CajaDeAhorro cajaFinal = new CajaDeAhorro();
        cajaFinal.setSaldo(BigDecimal.valueOf(300));

    //respuestas simuladas que devuelven objetos
        Mockito.when(repoCajaAhorro.findByCbu("cbuInicio")).thenReturn(Optional.of(cajaInicio));
        Mockito.when(repoCajaAhorro.findByCbu("cbuFinal")).thenReturn(Optional.of(cajaFinal));

        servicioCajaAhorro.transferenciaCbu(requestDTO);

        //verifico el saldo inicial y final de la caja
        assertEquals(new BigDecimal(100), cajaInicio.getSaldo());
        assertEquals(new BigDecimal(400), cajaFinal.getSaldo());
    }

    @Test
    @DisplayName("Test crear caja de ahorro")

    public void testFondosInsuficientes() {
        TranferenciaRequestDTO requestDTO = new TranferenciaRequestDTO();
        requestDTO.setCbuInicio("someCbuInicio");
        requestDTO.setCbuFinal("someCbuFinal");
        requestDTO.setMonto(BigDecimal.valueOf(500));

        CajaDeAhorro cajaInicio = new CajaDeAhorro();
        cajaInicio.setSaldo(BigDecimal.valueOf(200));

        CajaDeAhorro cajaFinal = new CajaDeAhorro();
        cajaFinal.setSaldo(BigDecimal.valueOf(300));

        Mockito.when(repoCajaAhorro.findByCbu("someCbuInicio")).thenReturn(Optional.of(cajaInicio));
        Mockito.when(repoCajaAhorro.findByCbu("someCbuFinal")).thenReturn(Optional.of(cajaFinal));

        assertThrows(Exception.class, () -> servicioCajaAhorro.transferenciaCbu(requestDTO));

        Mockito.verify(repoCajaAhorro, never()).save(any(CajaDeAhorro.class));
    }

    @Test
    @DisplayName("Test comprobar fondos disponibles")

    public void testComprobarFondos() throws Exception {
        CajaDeAhorro inicio = new CajaDeAhorro();
        inicio.setSaldo(new BigDecimal("200.00"));
        BigDecimal monto = new BigDecimal("100.00");

        //method me permite traer los metodos privados y ejecutarlos para poder testear
        //le cambia la accesibilidad al metodo
        Method comprobarFondosMethod = ServicioCajaAhorro.class.getDeclaredMethod("comprobarFondos", CajaDeAhorro.class, BigDecimal.class);
        comprobarFondosMethod.setAccessible(true);

        assertDoesNotThrow(() -> comprobarFondosMethod.invoke(servicioCajaAhorro, inicio, monto));
    }


    @Test
    @DisplayName("Test generar cbu")

    public void testGenerarCbuRandom() throws Exception {
        //method me permite traer los metodos privados y ejecutarlos para poder testear
        //le cambia la accesibilidad al metodo
        Method generarCbuRamdonMethod = ServicioCajaAhorro.class.getDeclaredMethod("generarCbuRamdon");
        generarCbuRamdonMethod.setAccessible(true);

        String cbu = (String) generarCbuRamdonMethod.invoke(servicioCajaAhorro);

        int expectedLength = 22; //si no es 22, da error
        assertEquals(expectedLength, cbu.length(), "Longitud del CBU generada incorrecta");
    }

}