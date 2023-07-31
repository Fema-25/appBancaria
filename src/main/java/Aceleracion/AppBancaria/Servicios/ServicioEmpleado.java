package Aceleracion.AppBancaria.Servicios;

import Aceleracion.AppBancaria.Entidades.Dto.Request.EmpleadoRequestDTO;
import Aceleracion.AppBancaria.Entidades.Empleado;
import Aceleracion.AppBancaria.Mapper.EmpleadoMapper;
import Aceleracion.AppBancaria.Mapper.EmpleadoMapperImpl;
import Aceleracion.AppBancaria.Repositorios.RepositorioEmpleado;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioEmpleado {
    private RepositorioEmpleado repositorioEmpleado;
    private ServicioSucursal servicioSucursal;

    public ServicioEmpleado(RepositorioEmpleado repositorioEmpleado, ServicioSucursal servicioSucursal) {
        this.repositorioEmpleado = repositorioEmpleado;
        this.servicioSucursal = servicioSucursal;
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
}
