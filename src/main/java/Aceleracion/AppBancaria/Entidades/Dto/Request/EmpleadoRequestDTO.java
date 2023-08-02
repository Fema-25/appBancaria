package Aceleracion.AppBancaria.Entidades.Dto.Request;

public class EmpleadoRequestDTO {
    private String nombre;
    private String apellido;
    private String dni;
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
