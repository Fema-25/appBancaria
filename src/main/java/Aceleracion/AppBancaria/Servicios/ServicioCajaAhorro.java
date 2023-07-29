package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.CajaDeAhorro;
import Aceleracion.AppBancaria.Entidades.Cliente;
import Aceleracion.AppBancaria.Entidades.Dto.Response.CajaAhorroDTO;
import Aceleracion.AppBancaria.Repositorios.RepositorioCajaAhorro;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
    public void ingresarDineroCajaAhorro(CajaAhorroDTO cajaAhorroDTO) throws Exception {
        Optional<CajaDeAhorro>caja = repoCajaAhorro.findById(cajaAhorroDTO.getId());
        if(!caja.isPresent()){
            throw new Exception("Lo siento no se encontro su caja de ahorro verifique bien los datos ingresados");
        }else {
            CajaDeAhorro bD = caja.get();
            bD.setSaldo(bD.getSaldo().add(cajaAhorroDTO.getSaldo()));
            repoCajaAhorro.save(bD);
        }
    }

    public void retirarDineroCajaAhorro(CajaAhorroDTO cajaAhorroDTO) throws Exception {
        Optional<CajaDeAhorro>caja = repoCajaAhorro.findById(cajaAhorroDTO.getId());
        if(!caja.isPresent()){
            throw new Exception("Lo siento no se encontro su caja de ahorro verifique bien los datos ingresados");
        }else {
            CajaDeAhorro bD = caja.get();
            comprobraFondos(cajaAhorroDTO,bD);
            bD.setSaldo(bD.getSaldo().subtract(cajaAhorroDTO.getSaldo()));
            repoCajaAhorro.save(bD);

        }
    }

    private void comprobraFondos(CajaAhorroDTO cajaAhorroDTO, CajaDeAhorro bD) throws Exception {
        if(cajaAhorroDTO.getSaldo().compareTo(bD.getSaldo()) > 0){
            throw new Exception("Su cuente no posee los fondos que solicita");
        }
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
