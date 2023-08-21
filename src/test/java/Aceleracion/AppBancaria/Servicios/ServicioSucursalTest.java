package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.Banco;
import Aceleracion.AppBancaria.Entidades.Dto.Request.SucursalRequestDTO;
import Aceleracion.AppBancaria.Entidades.Sucursal;
import Aceleracion.AppBancaria.Repositorios.RepositorioSucursal;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ServicioSucursalTest {

    private ServicioSucursal servSucursal;

    private ServicioBanco servBancoMock;

    private RepositorioSucursal repoSucursalMock;
    private SucursalRequestDTO sucursalPruebaDTO;

    @BeforeEach
    void setUp() {
       servBancoMock = Mockito.mock(ServicioBanco.class);
       repoSucursalMock = Mockito.mock(RepositorioSucursal.class);
        servSucursal = new ServicioSucursal(servBancoMock,repoSucursalMock);
        sucursalPruebaDTO = new SucursalRequestDTO("prueba","calle 123",1l);
    }

    @Test
    void crearSucursal() {
        Banco banco = new Banco();
        banco.setId(1l);
        banco.setNombre("banco Prueba");
        ArgumentCaptor<Sucursal> sucursalCaptor = ArgumentCaptor.forClass(Sucursal.class);

        try {
            Mockito.when(servBancoMock.buscarBanco(sucursalPruebaDTO.getIdBanco())).thenReturn(banco);
        }catch (Exception e){
            fail(e.getMessage());
        }


        Assertions.assertDoesNotThrow(()->servSucursal.crearSucursal(sucursalPruebaDTO));
        Mockito.verify(repoSucursalMock,Mockito.times(1)).save(Mockito.any(Sucursal.class));
        Mockito.verify(repoSucursalMock).save(sucursalCaptor.capture());

        Sucursal salida = sucursalCaptor.getValue();

        Assertions.assertEquals("prueba", salida.getNombreSucursal());
        Assertions.assertEquals("calle 123", salida.getDirrecion());
        Assertions.assertNotNull(salida.getBanco());



    }

    @Test
    void buscarSucursal() {
        Sucursal bd = new Sucursal();
        bd.setId(1l);
        bd.setNombreSucursal("Prueba");
        Mockito.when(repoSucursalMock.findById(1l)).thenReturn(Optional.of(bd));
        Sucursal salida;
        salida = Assertions.assertDoesNotThrow(()->servSucursal.buscarSucursal(1l));
        Assertions.assertEquals("Prueba",salida.getNombreSucursal());
        Assertions.assertEquals(1l , salida.getId());

    }
}