package Aceleracion.AppBancaria.Entidades.Dto.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class EmpleadoRequestDTO {
    @NotBlank(message = "Ingrese un nombre")
    private String nombre;
    @NotBlank(message = "Ingrese un apellido")
    private String apellido;
    @NotBlank(message = "Por favor ingrese un dni")
    @Pattern(message = "Ingrese un numero de dni valido",regexp ="\\b\\d{8}\\b" )
    private String dni;
    @Min(value = 1, message = "El id del banco debe ser mayor o igual a 1")
    private Long idSucursal;

    public EmpleadoRequestDTO() {
    }

    public EmpleadoRequestDTO(String nombre, String apellido, Long idSucursal,String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.idSucursal = idSucursal;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
