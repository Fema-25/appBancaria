package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.*;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestActualizarDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Request.ClienteRequestDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Request.SolicitudBajaDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Request.TranferenciaRequestDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.CajaAhorroDTO;
import Aceleracion.AppBancaria.Entidades.Dto.Response.ClienteResponseActulizarDTO;
import Aceleracion.AppBancaria.Mapper.*;
import Aceleracion.AppBancaria.Repositorios.RepositorioCliente;
import Aceleracion.AppBancaria.Repositorios.RepositorioSolicitudBaja;
import Aceleracion.AppBancaria.Repositorios.RepositorioSolicitudCuentaCorriente;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.*;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ServicioCliente {


    private final RepositorioCliente repoCliente;
    private RepositorioSolicitudBaja repoSolicitudBaja;
    private ServicioCajaAhorro servCajaAhorro;
    private ServicioSucursal servSucursal;
    private RepositorioSolicitudCuentaCorriente repoSolicitudCuenta;




    public ServicioCliente(RepositorioCliente repoClinte, ServicioCajaAhorro servCajaAhorro, ServicioSucursal servSucursal, RepositorioSolicitudBaja repoSolicitudBaja, RepositorioSolicitudCuentaCorriente repoSolicitudCuenta) {
        this.repoCliente = repoClinte;
        this.servCajaAhorro = servCajaAhorro;
        this.servSucursal = servSucursal;
        this.repoSolicitudBaja = repoSolicitudBaja;


        this.repoSolicitudCuenta = repoSolicitudCuenta;
    }
    @Transactional
    public void crearCliente(@Valid ClienteRequestDTO clienteDto)  throws Exception{

        Optional<Cliente> cliente = repoCliente.findByDni(clienteDto.getDni());

        if(cliente.isPresent()){
            throw new Exception("El dni con el que esta ingreasndo ya esta registrado");
        }
        Cliente persiste = ClienteMapper.INSTANCE.clienteResponseDtoToCliente(clienteDto);
        persiste.setSucursal(servSucursal.buscarSucursal(clienteDto.getSucursalId()));
        persiste.setAlta(true);
        repoCliente.save(persiste);


    }

    public ClienteResponseActulizarDTO actualizarDatos(ClienteRequestActualizarDTO clienteRequestActualizarDTO) throws Exception {
            ClienteMapper mapper = new ClienteMapperImpl();
            Optional<Cliente> bD = repoCliente.findById(clienteRequestActualizarDTO.getId());
            if(bD.isPresent()){
                Cliente cliente = bD.get();
                mapper.actualizarDatosDtoToClinte(clienteRequestActualizarDTO,cliente);
                repoCliente.save(cliente);
                return mapper.clienteToClienteResposeActualizarDTO(cliente);
            }
            else{
               throw new Exception("No se pudo encontrar el usuario");
            }
    }
    public void crearCajaDeAhorro(Long id) throws Exception{
        Optional<Cliente> bD = repoCliente.findById(id);
        if(bD.isPresent()){
            Cliente cliente = bD.get();
            List<CajaDeAhorro> cajas = cliente.getCajaDeAhorro();
            cajas.add(servCajaAhorro.crearCajaAhorro(cliente));
            cliente.setCajaDeAhorro(cajas);
            repoCliente.save(cliente);
        }
        else{
            throw new Exception("Error al encotrar el User en la base de datos intentelo mas tarde");
        }
    }
    public List<CajaAhorroDTO>listaCajasAhorro(Long id) throws Exception{
        CajaAhorroMapper cajaAhorroMapper = new CajaAhorroMapperImpl();
        Optional<Cliente> bD = repoCliente.findById(id);
        if(bD.isPresent()){
            Cliente cliente = bD.get();
            return cajaAhorroMapper.listCajaDeAhorroToListCajaAhorroDTO(cliente.getCajaDeAhorro());
        }
        else {
            throw new Exception("Error al encotrar el User en la base de datos intentelo mas tarde");
        }
    }

    public void ingresarDineroCajaAhorro(CajaAhorroDTO cajaAhorroDTO) throws Exception {
            servCajaAhorro.ingresarDineroCajaAhorro(cajaAhorroDTO);
    }

    public void retirarDineroCajaAhorro(CajaAhorroDTO cajaAhorroDTO) throws Exception {
        servCajaAhorro.retirarDineroCajaAhorro(cajaAhorroDTO);
    }

    public void transferenciaCbu(TranferenciaRequestDTO tranferenciaRequestDTO) throws Exception{
        servCajaAhorro.transferenciaCbu(tranferenciaRequestDTO);
    }
    public void solicitarBaja(SolicitudBajaDTO solicitudBajaDTO) throws Exception {
        SolicitudBajaMapper mapper = new SolicitudBajaMapperImpl();
        SolicitudBaja solicitudBaja = mapper.solicitudBajaDtoToSolicitudBaja(solicitudBajaDTO);
        solicitudBaja.setCliente(repoCliente.getById(solicitudBajaDTO.getIdCliente()));
        repoSolicitudBaja.save(solicitudBaja);


    }
    public void SolicitarCuentaCorriente(long idCliente) throws Exception {
        Optional<Cliente>bD = repoCliente.findById(idCliente);
        if(!bD.isPresent()){
            throw new Exception("No se encontro el cliente");
        }
        else{
            SolicitudCuenteCorriente solicitud = new SolicitudCuenteCorriente();
            solicitud.setCliente(bD.get());
            repoSolicitudCuenta.save(solicitud);

        }
    }

}
