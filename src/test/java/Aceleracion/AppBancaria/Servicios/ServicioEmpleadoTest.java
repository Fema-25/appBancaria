package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Entidades.Dto.Request.EmpleadoRequestDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.SolicitudBajaResponseDTO;
import Aceleracion.AppBancaria.Entidades.Empleado;
import Aceleracion.AppBancaria.Entidades.SolicitudBaja;
import Aceleracion.AppBancaria.Entidades.Sucursal;
import Aceleracion.AppBancaria.Repositorios.RepositorioCliente;
import Aceleracion.AppBancaria.Repositorios.RepositorioEmpleado;
import Aceleracion.AppBancaria.Repositorios.RepositorioSolicitudBaja;
import Aceleracion.AppBancaria.Repositorios.RepositorioSucursal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ServicioEmpleadoTest {
    @InjectMocks
    private ServicioEmpleado servicioEmpleado;

    @Mock
    private RepositorioEmpleado repositorioEmpleado;

    @Mock
    private ServicioSucursal servicioSucursal;

    @Mock
    private RepositorioSolicitudBaja repositorioSolicitudBaja;

    @Mock
    private RepositorioCliente repositorioCliente;

    @Mock
    private ServicioCuentaCorriente servicioCuentaCorriente;


    @BeforeEach
    void setUp() {

    }

    @Test
    public void crearEmpleado_Exito() throws Exception {
        EmpleadoRequestDTO requestDTO = new EmpleadoRequestDTO("John", "Doe", 1L, "12345678");
        Sucursal sucursal = new Sucursal();
        when(servicioSucursal.buscarSucursal(1L)).thenReturn(sucursal);
        when(repositorioEmpleado.findByDni("12345678")).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> servicioEmpleado.crearEmpleado(requestDTO));

        verify(repositorioEmpleado, times(1)).save(any(Empleado.class));
    }

    @Test()
    public void crearEmpleado_Dni_Duplicado() throws Exception {
        EmpleadoRequestDTO requestDTO = new EmpleadoRequestDTO("Nombre", "Apellido", 1L, "12345678");
        Empleado empleado = new Empleado("Nombre", "Apellido", "12345678");

        when(repositorioEmpleado.findByDni(requestDTO.getDni())).thenReturn(Optional.of(empleado));

        Throwable exception = assertThrows(Exception.class,()-> servicioEmpleado.crearEmpleado(requestDTO));

        assertEquals("Error se encontro otro empleado con el mismo dni",exception.getMessage());
    }


    @Test
    public void testListarSolicitudesBaja_Exito() throws Exception {
        long idSucursal = 1L;

        Sucursal sucursal = new Sucursal();
        when(servicioSucursal.buscarSucursal(idSucursal)).thenReturn(sucursal);

        List<SolicitudBaja> solicitudBajaList = new ArrayList<>();
        solicitudBajaList.add(new SolicitudBaja());
        solicitudBajaList.add(new SolicitudBaja());
        when(repositorioSolicitudBaja.listarSolicitudBaja(sucursal)).thenReturn(solicitudBajaList);

        List<SolicitudBajaResponseDTO> respuesta = servicioEmpleado.listarSolicitudesBaja(idSucursal);

        verify(servicioSucursal, times(1)).buscarSucursal(idSucursal);
        verify(repositorioSolicitudBaja, times(1)).listarSolicitudBaja(sucursal);

        assertEquals(solicitudBajaList.size(), respuesta.size());
    }

    @Test
    public void testListarSolicitudesBaja_SucursalNoEncontrada() throws Exception {
        long idSucursal = 1L;

        when(servicioSucursal.buscarSucursal(idSucursal)).thenThrow(new Exception("No Se encontro la sucursal"));

        Throwable exception = assertThrows(Exception.class, () -> servicioEmpleado.listarSolicitudesBaja(idSucursal));

        assertEquals("No Se encontro la sucursal",exception.getMessage());
    }

    @Test
    public void testListarSolicitudesBaja_SinSolicitudes() throws Exception {
        long idSucursal = 1L;

        Sucursal sucursal = new Sucursal();

        when(servicioSucursal.buscarSucursal(idSucursal)).thenReturn(sucursal);

        when(repositorioSolicitudBaja.listarSolicitudBaja(sucursal)).thenReturn(new ArrayList<>());

        List<SolicitudBajaResponseDTO> respuesta = servicioEmpleado.listarSolicitudesBaja(idSucursal);

        assertTrue(respuesta.isEmpty());
    }

    @Test
    public void testDarDebaja_Exito() throws Exception {
        long idCliente = 1L;
        long idBaja = 2L;
        Cliente cliente = new Cliente();
        SolicitudBaja solicitudBaja = new SolicitudBaja();

        when(repositorioSolicitudBaja.findById(idBaja)).thenReturn(Optional.of(solicitudBaja));
        when(repositorioCliente.findById(idCliente)).thenReturn(Optional.of(cliente));

        assertDoesNotThrow(() -> servicioEmpleado.darDebaja(idCliente, idBaja));

        assertFalse(cliente.getAlta());

        verify(repositorioSolicitudBaja, times(1)).delete(solicitudBaja);
        verify(repositorioCliente, times(1)).save(cliente);
    }

    @Test
    public void testDarDebaja_DatosErroneos() throws Exception {
        long idCliente = 1L;
        long idBaja = 2L;

        when(repositorioSolicitudBaja.findById(idBaja)).thenReturn(Optional.empty());
        when(repositorioCliente.findById(idCliente)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> servicioEmpleado.darDebaja(idCliente, idBaja));

        assertEquals("Error los datos ingresados don Erroneos", exception.getMessage());
    }

    @Test
    public void testAprobarCuentaCorriente_Exito() throws Exception {
        long idCliente = 1L;
        Cliente cliente = new Cliente();

        when(repositorioCliente.findById(idCliente)).thenReturn(Optional.of(cliente));

        assertDoesNotThrow(() -> servicioEmpleado.AprobarCuentaCorriente(idCliente));

        verify(servicioCuentaCorriente, times(1)).CrearCuentaCorriente(cliente);
    }

    @Test
    public void testAprobarCuentaCorriente_ClienteNoEncontrado() {
        long idCliente = 1L;

        when(repositorioCliente.findById(idCliente)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> servicioEmpleado.AprobarCuentaCorriente(idCliente));

        assertEquals("El cliente no se encontro", exception.getMessage());

        verify(servicioCuentaCorriente, never()).CrearCuentaCorriente(any(Cliente.class));
    }

}