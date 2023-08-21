package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Entidades.CuentaCorriente;
import Aceleracion.AppBancaria.Repositorios.RepositorioCuentaCorriente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ServicioCuentaCorrienteTest {

    @BeforeEach
    void setUp() {
    }

    @Mock
    private RepositorioCuentaCorriente repositorioCuentaCorriente;

    @InjectMocks
    private ServicioCuentaCorriente servicioCuentaCorriente;

    @Test
    public void testCrearCuentaCorriente_Exito() {
        Cliente cliente = new Cliente();

        when(repositorioCuentaCorriente.save(any(CuentaCorriente.class))).thenReturn(new CuentaCorriente());

        assertDoesNotThrow(() -> servicioCuentaCorriente.CrearCuentaCorriente(cliente));

        verify(repositorioCuentaCorriente, times(1)).save(any(CuentaCorriente.class));
    }
}