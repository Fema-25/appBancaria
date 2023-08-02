package Aceleracion.AppBancaria.Entidades.Dto.Request;

import javax.validation.constraints.NotBlank;

public class BancoRequestDTO {
    @NotBlank(message = "Por favor ingrese un nombre para el banco")
    private String Nombre;

    public BancoRequestDTO() {
    }

    public BancoRequestDTO(String nombre) {
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
