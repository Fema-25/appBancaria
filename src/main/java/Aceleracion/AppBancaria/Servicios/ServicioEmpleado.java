package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.*;
import Aceleracion.AppBancaria.Entidades.Dto.Request.EmpleadoRequestDTO;

import Aceleracion.AppBancaria.Entidades.Dto.Response.SolicitudBajaResponseDTO;
import Aceleracion.AppBancaria.Mapper.EmpleadoMapper;
import Aceleracion.AppBancaria.Mapper.EmpleadoMapperImpl;
import Aceleracion.AppBancaria.Mapper.SolicitudBajaMapper;

import Aceleracion.AppBancaria.Repositorios.RepositorioCliente;
import Aceleracion.AppBancaria.Repositorios.RepositorioEmpleado;
import Aceleracion.AppBancaria.Repositorios.RepositorioSolicitudBaja;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioEmpleado {
    private RepositorioEmpleado repositorioEmpleado;
    private ServicioSucursal servicioSucursal;
    private RepositorioSolicitudBaja repositorioSolicitudBaja;
    private RepositorioCliente repositorioCliente;
    private ServicioCuentaCorriente servicioCuentaCorriente;

    public ServicioEmpleado(RepositorioEmpleado repositorioEmpleado, ServicioSucursal servicioSucursal, RepositorioSolicitudBaja repositorioSolicitudBaja, RepositorioCliente repositorioCliente, ServicioCuentaCorriente servicioCuentaCorriente) {
        this.repositorioEmpleado = repositorioEmpleado;
        this.servicioSucursal = servicioSucursal;
        this.repositorioSolicitudBaja = repositorioSolicitudBaja;
        this.repositorioCliente = repositorioCliente;
        this.servicioCuentaCorriente = servicioCuentaCorriente;
    }

    public void crearEmpleado(EmpleadoRequestDTO empleadoRequestDTO) throws Exception {
       EmpleadoMapper mapper = new EmpleadoMapperImpl();
       Optional<Empleado>bD = repositorioEmpleado.findByDni(empleadoRequestDTO.getDni());
       if(bD.isPresent()){
           throw new Exception("Error se encontro otro empleado con el mismo dni");
       }else {
           Empleado persiste = mapper.empleadoRequestDtoToEmpleado(empleadoRequestDTO);
           persiste.setSucursal(servicioSucursal.buscarSucursal(empleadoRequestDTO.getIdSucursal()));
           repositorioEmpleado.save(persiste);

       }

    }
    public List<SolicitudBajaResponseDTO> listarSolicitudesBaja(long idSucursal) throws Exception {
        //SolicitudBajaMapper mapper = new SolicitudBajaMapperImpl();
        Sucursal sucursal = servicioSucursal.buscarSucursal(idSucursal);
        List<SolicitudBaja>lista = repositorioSolicitudBaja.listarSolicitudBaja(sucursal);
        List<SolicitudBajaResponseDTO> repuesta = SolicitudBajaMapper.INSTANCE.solicitudBajaListToSolicitudBajaResponseDtoList(lista);
        return repuesta;

    }
    public void darDebaja(long idCliente, long idBaja) throws Exception {
        Optional<SolicitudBaja>bajaBd = repositorioSolicitudBaja.findById(idBaja);
        Optional<Cliente>clienteBd = repositorioCliente.findById(idCliente);
        if(!bajaBd.isPresent() || !clienteBd.isPresent()){
            throw new Exception("Error los datos ingresados don Erroneos");
        }
        else{
            Cliente cliente = clienteBd.get();
            SolicitudBaja baja = bajaBd.get();
            cliente.setAlta(false);
            repositorioSolicitudBaja.delete(baja);
            repositorioCliente.save(cliente);

        }
    }
    public void AprobarCuentaCorriente(long idCliente) throws Exception {
        Optional<Cliente>bD=repositorioCliente.findById(idCliente);
        if(!bD.isPresent()){
            throw new Exception("El cliente no se encontro");
        }else{
            Cliente cliente = bD.get();
            servicioCuentaCorriente.CrearCuentaCorriente(cliente);
        }
    }
}
