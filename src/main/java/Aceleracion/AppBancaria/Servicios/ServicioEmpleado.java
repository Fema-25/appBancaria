package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.Dto.Request.EmpleadoRequestDTO;

import Aceleracion.AppBancaria.Entidades.Dto.Response.SolicitudBajaResponseDTO;
import Aceleracion.AppBancaria.Entidades.Empleado;
import Aceleracion.AppBancaria.Entidades.SolicitudBaja;
import Aceleracion.AppBancaria.Entidades.Sucursal;
import Aceleracion.AppBancaria.Mapper.EmpleadoMapper;
import Aceleracion.AppBancaria.Mapper.EmpleadoMapperImpl;
import Aceleracion.AppBancaria.Mapper.SolicitudBajaMapper;

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

    public ServicioEmpleado(RepositorioEmpleado repositorioEmpleado, ServicioSucursal servicioSucursal,RepositorioSolicitudBaja repositorioSolicitudBaja) {
        this.repositorioEmpleado = repositorioEmpleado;
        this.servicioSucursal = servicioSucursal;
        this.repositorioSolicitudBaja = repositorioSolicitudBaja;
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
    public void darDebaja(){

    }
    public void listarSolicitudesCuenteCorriente(){

    }
}
