package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.CajaDeAhorro;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestActualizarDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Request.SolicitudBajaDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.CajaAhorroDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.ClienteResponseActulizarDTO;
import Aceleracion.AppBancaria.Entidades.SolicitudBaja;
import Aceleracion.AppBancaria.Entidades.SolicitudCuenteCorriente;
import Aceleracion.AppBancaria.Repositorios.RepositorioCliente;
import Aceleracion.AppBancaria.Repositorios.RepositorioSolicitudBaja;
import Aceleracion.AppBancaria.Repositorios.RepositorioSolicitudCuentaCorriente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestDTO;

import org.junit.jupiter.api.DisplayName;

import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class ServicioClienteTest {
    private ServicioCliente servicioCliente;
    private RepositorioCliente repositorioClienteMock;
    private ServicioSucursal servicioSucursalMock;

    private RepositorioSolicitudBaja repositorioSolicitudBajaMock;
    private ServicioCajaAhorro servicioCajaAhorroMock;

    private RepositorioSolicitudCuentaCorriente repositorioSolicitudCuentaCorrienteMock;
    private ClienteRequestDTO clienteDTO;

    @BeforeEach
    void setUp() {
        repositorioClienteMock = Mockito.mock(RepositorioCliente.class);
        servicioSucursalMock = Mockito.mock(ServicioSucursal.class);
        servicioCajaAhorroMock = Mockito.mock(ServicioCajaAhorro.class);
        repositorioSolicitudBajaMock = Mockito.mock(RepositorioSolicitudBaja.class);
        repositorioSolicitudCuentaCorrienteMock = Mockito.mock(RepositorioSolicitudCuentaCorriente.class);
        clienteDTO = new ClienteRequestDTO("prueba@gmail.com", "1234",
                "pruebaNombre", "pruebaApellido",
                "41663302", "1558212011", 1L);


        servicioCliente = new ServicioCliente(repositorioClienteMock,servicioCajaAhorroMock,servicioSucursalMock,repositorioSolicitudBajaMock,repositorioSolicitudCuentaCorrienteMock);
    }


    @Test
    @DisplayName("Prueba de creación de cliente con DNI no registrado")
    void crearCliente_DniNoRegistrado() throws Exception {

        servicioCliente.crearCliente(clienteDTO);

        Mockito.verify(repositorioClienteMock).save(Mockito.any(Cliente.class));

        // Verifica el resultado esperado
        ArgumentCaptor<Cliente> clienteCaptor = ArgumentCaptor.forClass(Cliente.class);
        Mockito.verify(repositorioClienteMock).save(clienteCaptor.capture());

        Cliente clienteCreado = clienteCaptor.getValue();
        assertNotNull(clienteCreado,"Error el se esta persistiendo un cliente vacio");
        assertEquals("prueba@gmaial.com", clienteCreado.getEmail(),"No son iguales los Email");
        assertEquals("1234", clienteCreado.getPassword(),"se comprueba el Psw");
        // Verifica otras propiedades aquí...
    }

    @Test
    @DisplayName("Prueba de aborto cliente con el mismo DNI")
    void crearClienteDniRegistrado() {

        Mockito.when(repositorioClienteMock.findByDni(clienteDTO.getDni())).thenReturn(Optional.of( new Cliente()));
        assertThrows(Exception.class,() -> servicioCliente.crearCliente(clienteDTO),"Deberia haber lanzado una exeption ");
        Mockito.verify(repositorioClienteMock, Mockito.never()).save(Mockito.any(Cliente.class));


    }



    @Test
    @DisplayName("Prueba de actualizacion de datos")
    void actualizarDatos() throws Exception {
        ClienteRequestActualizarDTO datosEntrada = new ClienteRequestActualizarDTO(1L,"prueba@gmial.com","NombrePrueba",
                "ApellidoPrueba","41663302","1558212011");
        ClienteResponseActulizarDTO datosSalida = new ClienteResponseActulizarDTO();

        Mockito.when(repositorioClienteMock.findById(datosEntrada.getId())).thenReturn(Optional.of(new Cliente()));

        try {
            datosSalida = servicioCliente.actualizarDatos(datosEntrada);
        }catch (Exception e){
            fail(e.getMessage());
        }
        Assertions.assertEquals("prueba@gmial.com",datosSalida.getEmail(),"no coiciden los emails");
        Assertions.assertEquals("NombrePrueba",datosSalida.getNombre(),"No coiciden los nombres");
        Assertions.assertEquals("41663302",datosSalida.getDni(),"no coinciden los dni");

    }

    @Test
    void crearCajaDeAhorro() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        ArgumentCaptor<Cliente> clienteCaptor = ArgumentCaptor.forClass(Cliente.class);

        Mockito.when(repositorioClienteMock.findById(cliente.getId())).thenReturn(Optional.of(cliente));
        Mockito.when(servicioCajaAhorroMock.crearCajaAhorro(cliente)).thenReturn(new CajaDeAhorro());


        Assertions.assertDoesNotThrow(() ->servicioCliente.crearCajaDeAhorro(cliente.getId()));


        Mockito.verify(repositorioClienteMock,Mockito.times(1)).save(Mockito.any(Cliente.class));
        Mockito.verify(repositorioClienteMock).save(clienteCaptor.capture());
        Cliente clientePersistido = clienteCaptor.getValue();
        Assertions.assertNotNull(cliente.getCajaDeAhorro());

    }

    @Test
    void listaCajasAhorro() {

        List<CajaDeAhorro> cajasAhorro = new ArrayList<>();
        cajasAhorro.add(new CajaDeAhorro());
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCajaDeAhorro(cajasAhorro);
        List<CajaAhorroDTO> salida = new ArrayList<>();

        Mockito.when(repositorioClienteMock.findById(cliente.getId())).thenReturn(Optional.of(cliente));
        salida = Assertions.assertDoesNotThrow(() -> servicioCliente.listaCajasAhorro(cliente.getId()));



        Assertions.assertNotNull(salida.size() > 0);
    }

    @Test
    void ingresarDineroCajaAhorro() {

    }

    @Test
    void retirarDineroCajaAhorro() {
    }

    @Test
    void transferenciaCbu() {
    }

    @Test
    void solicitarBaja() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        SolicitudBajaDTO entrada = new SolicitudBajaDTO(1l,1l,"Otros");
        ArgumentCaptor<SolicitudBaja> soliCaptor = ArgumentCaptor.forClass(SolicitudBaja.class);
        Mockito.when(repositorioClienteMock.findById(entrada.getIdCliente())).thenReturn(Optional.of(cliente));

        Assertions.assertDoesNotThrow(() ->servicioCliente.solicitarBaja(entrada),"No deberia lanzar un Exceptions");

        Mockito.verify(repositorioSolicitudBajaMock,Mockito.times(1)).save(Mockito.any(SolicitudBaja.class));
        Mockito.verify(repositorioSolicitudBajaMock).save(soliCaptor.capture());



        SolicitudBaja salida = soliCaptor.getValue();
        Assertions.assertEquals("Otros",salida.getMotivo());
        Assertions.assertNotNull(salida);

    }

    @Test
    void solicitarCuentaCorriente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        ArgumentCaptor<SolicitudCuenteCorriente> soliCaptor = ArgumentCaptor.forClass(SolicitudCuenteCorriente.class);
        Mockito.when(repositorioClienteMock.findById(cliente.getId())).thenReturn(Optional.of(cliente));

        Assertions.assertDoesNotThrow(()->servicioCliente.SolicitarCuentaCorriente(cliente.getId()));

        Mockito.verify(repositorioSolicitudCuentaCorrienteMock,Mockito.times(1)).save(Mockito.any(SolicitudCuenteCorriente.class));
        Mockito.verify(repositorioSolicitudCuentaCorrienteMock).save(soliCaptor.capture());

        SolicitudCuenteCorriente salida = soliCaptor.getValue();
        Assertions.assertNotNull(salida,"LA SALIDA ES NULA");

    }
}