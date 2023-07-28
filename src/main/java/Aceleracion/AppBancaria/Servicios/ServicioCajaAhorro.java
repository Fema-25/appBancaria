package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.CajaDeAhorro;
import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Repositorios.RepositorioCajaAhorro;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ServicioCajaAhorro {
    private RepositorioCajaAhorro repoCajaAhorro;

    public ServicioCajaAhorro(RepositorioCajaAhorro repoCajaAhorro){
        this.repoCajaAhorro= repoCajaAhorro;
    }

    public CajaDeAhorro crearCajaAhorro(Cliente cliente){
        CajaDeAhorro cajaDeAhorro = new CajaDeAhorro();
        cajaDeAhorro.setCbu(generarCbuRamdon());
        cajaDeAhorro.setCliente(cliente);
        repoCajaAhorro.save(cajaDeAhorro);
        return cajaDeAhorro;
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
