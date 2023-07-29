package Aceleracion.AppBancaria.Entidades.Dto.Request;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class ClienteRequestDTO {
    @NotNull
    @NotBlank(message = "Por favor ingrese un email")
    @Email(message = "Por favor ingrese un email valido")
    private String email;
    @Size(min = 8,max = 16,message = "El contraseña solo puede contener entre 8-16 caracteres")
    @NotBlank
    private String password;
    @NotBlank(message = "Por favor ingrese un nombre")
    private String nombre;
    @NotBlank(message = "Por favor ingrese un apellido")
    private String apellido;
    @NotBlank(message = "Por favor ingrese un dni")
    @Pattern(message = "Ingrese un numero de dni valido",regexp ="\\b\\d{8}\\b" )
    private String dni;
    @NotBlank(message = "Por favor ingrese un telefono")
    @Pattern(message = "Por favor ingrese un numero de telefono valido", regexp = "\\b\\d{10}\\b")
    private String telefono;

    public ClienteRequestDTO(String email, String password, String nombre, String apellido, String dni, String telefono) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
