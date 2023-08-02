package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Entidades.CuentaCorriente;
import Aceleracion.AppBancaria.Repositorios.RepositorioCuentaCorriente;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ServicioCuentaCorriente {
    private RepositorioCuentaCorriente repositorioCuentaCorriente;

    public ServicioCuentaCorriente(RepositorioCuentaCorriente repositorioCuentaCorriente) {
        this.repositorioCuentaCorriente = repositorioCuentaCorriente;
    }
    public void CrearCuentaCorriente(Cliente cliente){
        CuentaCorriente cuentaCorriente = new CuentaCorriente();
        cuentaCorriente.setCliente(cliente);
        cuentaCorriente.setCbu(generarCbuRamdon());
        repositorioCuentaCorriente.save(cuentaCorriente);

    }
    private String generarCbuRamdon(){
        int cbuLength = 22;
        Random random = new Random();
        StringBuilder cbuBuilder = new StringBuilder(cbuLength);

        for (int i = 0; i < cbuLength; i++) {
            int randomDigit = random.nextInt(10);
            cbuBuilder.append(randomDigit);
        }

        return cbuBuilder.toString();
    }
}
