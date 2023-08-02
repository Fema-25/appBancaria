package Aceleracion.AppBancaria.Entidades.Dto.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ClienteRequestActualizarDTO {
    private Long id ;

    @Email(message = "Por Favor ingrese un email valido")
    private String email;

    private String nombre;

    private String apellido;
    @Pattern(message = "Ingrese un numero de dni valido",regexp ="\\b\\d{8}\\b" )
    private String dni;
    @Pattern(message = "Por favor ingrese un numero de telefono valido", regexp = "\\b\\d{10}\\b")
    private String telefono;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
